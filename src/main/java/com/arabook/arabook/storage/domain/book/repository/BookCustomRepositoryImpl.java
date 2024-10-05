package com.arabook.arabook.storage.domain.book.repository;

import static com.arabook.arabook.common.exception.book.BookExceptionType.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.api.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.api.book.controller.dto.response.BookResponse;
import com.arabook.arabook.api.category.controller.dto.response.CategoryResponse;
import com.arabook.arabook.api.hashtag.controller.dto.response.HashTagResponse;
import com.arabook.arabook.common.exception.book.BookException;
import com.arabook.arabook.storage.domain.book.entity.Book;
import com.arabook.arabook.storage.domain.book.entity.QBestSeller;
import com.arabook.arabook.storage.domain.book.entity.QBook;
import com.arabook.arabook.storage.domain.book.entity.QBookCategoryMapping;
import com.arabook.arabook.storage.domain.book.entity.QBookHashtagMapping;
import com.arabook.arabook.storage.domain.category.entity.QCategory;
import com.arabook.arabook.storage.domain.hashtag.entity.QHashtag;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

  private final JPAQueryFactory queryFactory;

  private static final int BEST_SELLER_LIMIT = 18;

  @Override
  public List<BookResponse> findBooksBySearch(final String keyword) {
    QBook book = QBook.book;
    BooleanBuilder builder = new BooleanBuilder();

    builder.or(book.author.containsIgnoreCase(keyword));
    builder.or(book.title.containsIgnoreCase(keyword));
    builder.or(book.isbn.eq(keyword));

    return queryFactory
        .select(
            Projections.constructor(
                BookResponse.class, book.bookId, book.coverUrl, book.title, book.author))
        .from(book)
        .where(builder)
        .fetch();
  }

  @Override
  public BookDetailResponse findBookDetail(Long bookId) {
    QBook book = QBook.book;
    QCategory category = QCategory.category;
    QHashtag hashtag = QHashtag.hashtag;
    QBookCategoryMapping bookCategoryMapping = QBookCategoryMapping.bookCategoryMapping;
    QBookHashtagMapping bookHashtagMapping = QBookHashtagMapping.bookHashtagMapping;

    Book foundBook =
        Optional.ofNullable(queryFactory.selectFrom(book).where(book.bookId.eq(bookId)).fetchOne())
            .orElseThrow(() -> new BookException(BOOK_NOT_FOUND));

    List<CategoryResponse> categories =
        queryFactory
            .select(
                Projections.constructor(CategoryResponse.class, category.categoryId, category.name))
            .from(bookCategoryMapping)
            .leftJoin(category)
            .on(bookCategoryMapping.category.categoryId.eq(category.categoryId))
            .where(bookCategoryMapping.book.bookId.eq(bookId))
            .fetch();

    List<HashTagResponse> hashtags =
        queryFactory
            .select(Projections.constructor(HashTagResponse.class, hashtag.hashtagId, hashtag.name))
            .from(bookHashtagMapping)
            .leftJoin(hashtag)
            .on(bookHashtagMapping.hashtag.hashtagId.eq(hashtag.hashtagId))
            .where(bookHashtagMapping.book.bookId.eq(bookId))
            .fetch();

    // 카테고리와 해시태그가 없으면 빈 리스트로 처리
    return BookDetailResponse.of(
        foundBook,
        categories.isEmpty() ? Collections.emptyList() : categories,
        hashtags.isEmpty() ? Collections.emptyList() : hashtags);
  }

  public List<BookResponse> findBestSellerBooks() {
    QBook book = QBook.book;
    QBestSeller bestSeller = QBestSeller.bestSeller;
    return queryFactory
        .select(
            Projections.constructor(
                BookResponse.class, book.bookId, book.coverUrl, book.title, book.author))
        .from(book)
        .join(bestSeller)
        .on(book.isbn.eq(bestSeller.book.isbn))
        .orderBy(bestSeller.bookRank.asc())
        .limit(BEST_SELLER_LIMIT)
        .fetch();
  }
}

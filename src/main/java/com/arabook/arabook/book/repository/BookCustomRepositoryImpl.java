package com.arabook.arabook.book.repository;

import static com.arabook.arabook.common.exception.book.BookExceptionType.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.api.category.controller.dto.response.SubCategoryResponse;
import com.arabook.arabook.api.hashtag.controller.dto.response.HashTagResponse;
import com.arabook.arabook.book.controller.dto.response.AIRecommendBookResponse;
import com.arabook.arabook.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.book.controller.dto.response.BookResponse;
import com.arabook.arabook.book.entity.Book;
import com.arabook.arabook.common.exception.book.BookException;
import com.arabook.arabook.storage.domain.book.entity.QBestSeller;
import com.arabook.arabook.storage.domain.book.entity.QBook;
import com.arabook.arabook.storage.domain.book.entity.QBookHashtagMapping;
import com.arabook.arabook.storage.domain.book.entity.QBookSubCategoryMapping;
import com.arabook.arabook.storage.domain.category.entity.QSubCategory;
import com.arabook.arabook.storage.domain.hashtag.entity.QHashtag;
import com.arabook.arabook.storage.domain.member.entity.QAIRecommendation;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

  private final JPAQueryFactory queryFactory;

  private static final int BEST_SELLER_LIMIT = 18;
  private static final int BEST_SELLER_RANK_FIRST = 1;

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
    QSubCategory category = QSubCategory.subCategory;
    QHashtag hashtag = QHashtag.hashtag;
    QBookSubCategoryMapping bookCategoryMapping = QBookSubCategoryMapping.bookSubCategoryMapping;
    QBookHashtagMapping bookHashtagMapping = QBookHashtagMapping.bookHashtagMapping;

    Book foundBook =
        Optional.ofNullable(queryFactory.selectFrom(book).where(book.bookId.eq(bookId)).fetchOne())
            .orElseThrow(() -> new BookException(BOOK_NOT_FOUND));

    List<SubCategoryResponse> categories = getCategories(category, bookCategoryMapping, foundBook);

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

  @Override
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

  @Override
  public AIRecommendBookResponse findAIRecommendBookDefault() {
    QBook book = QBook.book;
    QBestSeller bestSeller = QBestSeller.bestSeller;
    QSubCategory category = QSubCategory.subCategory;
    QBookSubCategoryMapping bookCategoryMapping = QBookSubCategoryMapping.bookSubCategoryMapping;

    Book foundBook =
        queryFactory
            .selectFrom(book)
            .join(bestSeller)
            .on(book.isbn.eq(bestSeller.book.isbn))
            .where(bestSeller.bookRank.eq(BEST_SELLER_RANK_FIRST))
            .fetchFirst();

    List<SubCategoryResponse> categories = getCategories(category, bookCategoryMapping, foundBook);

    return AIRecommendBookResponse.of(
        foundBook.getBookId(),
        foundBook.getCoverUrl(),
        foundBook.getTitle(),
        foundBook.getAuthor(),
        categories);
  }

  @Override
  public AIRecommendBookResponse findAIRecommendBook(final Long memberId) {
    QBook book = QBook.book;
    QAIRecommendation aiRecommendation = QAIRecommendation.aIRecommendation;
    QSubCategory category = QSubCategory.subCategory;
    QBookSubCategoryMapping bookCategoryMapping = QBookSubCategoryMapping.bookSubCategoryMapping;

    Book foundBook =
        queryFactory
            .selectFrom(book)
            .join(aiRecommendation)
            .on(book.bookId.eq(aiRecommendation.book.bookId))
            .where(
                aiRecommendation
                    .member
                    .memberId
                    .eq(memberId)
                    .and(aiRecommendation.recommendationDate.eq(LocalDate.now())))
            .fetchFirst();

    boolean hasNotRecommendBook = foundBook == null;

    if (hasNotRecommendBook) {
      return findAIRecommendBookDefault();
    }

    List<SubCategoryResponse> categories = getCategories(category, bookCategoryMapping, foundBook);

    return AIRecommendBookResponse.of(
        foundBook.getBookId(),
        foundBook.getCoverUrl(),
        foundBook.getTitle(),
        foundBook.getAuthor(),
        categories);
  }

  private List<SubCategoryResponse> getCategories(
      QSubCategory category, QBookSubCategoryMapping bookCategoryMapping, Book foundBook) {
    return queryFactory
        .select(
            Projections.constructor(
                SubCategoryResponse.class, category.subCategoryId, category.subCategoryName))
        .from(bookCategoryMapping)
        .leftJoin(category)
        .on(bookCategoryMapping.subCategory.subCategoryId.eq(category.subCategoryId))
        .where(bookCategoryMapping.book.eq(foundBook))
        .fetch();
  }
}

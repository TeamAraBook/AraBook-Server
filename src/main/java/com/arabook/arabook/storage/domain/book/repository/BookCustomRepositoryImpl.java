package com.arabook.arabook.storage.domain.book.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.api.book.controller.dto.response.BookResponse;
import com.arabook.arabook.storage.domain.book.entity.QBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<BookResponse> findBooksBySearch(final String keyword) {
    QBook book = QBook.book;
    BooleanBuilder builder = new BooleanBuilder();

    builder.and(book.author.containsIgnoreCase(keyword));
    builder.and(book.title.containsIgnoreCase(keyword));
    builder.and(book.isbn.eq(keyword));

    return queryFactory
        .select(
            Projections.constructor(
                BookResponse.class, book.bookId, book.coverUrl, book.title, book.author))
        .from(book)
        .where(builder)
        .fetch();
  }
}

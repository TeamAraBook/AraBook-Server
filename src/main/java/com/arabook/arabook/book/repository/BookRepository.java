package com.arabook.arabook.book.repository;

import static com.arabook.arabook.common.exception.book.BookExceptionType.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.book.entity.Book;
import com.arabook.arabook.common.exception.book.BookException;

public interface BookRepository extends JpaRepository<Book, Long>, BookCustomRepository {
  Optional<Book> findByBookId(Long bookId);

  default Book findByBookIdOrThrow(Long bookId) {
    return findByBookId(bookId).orElseThrow(() -> new BookException(BOOK_NOT_FOUND));
  }
}

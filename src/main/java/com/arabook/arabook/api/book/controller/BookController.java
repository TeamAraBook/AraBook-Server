package com.arabook.arabook.api.book.controller;

import static com.arabook.arabook.common.success.book.BookSuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.api.book.service.BookService;
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController implements BookApi {
  private final BookService bookService;

  @Override
  @GetMapping("/{bookId}")
  public ResponseEntity<ResponseTemplate<BookDetailResponse>> getBookDetail(
      @PathVariable("bookId") final Long bookId) {
    BookDetailResponse response = bookService.getBookDetail(bookId);
    return ResponseEntity.ok(ResponseTemplate.success(GET_BOOK_DETAIL, response));
  }

  @Override
  @GetMapping("/search")
  public ResponseEntity<ResponseTemplate<BooksResponse>> getBooksBySearch(
      @RequestParam("keyword") final String keyword) {
    BooksResponse response = bookService.getBooksBySearch(keyword);
    return ResponseEntity.ok(ResponseTemplate.success(GET_BOOKS_BY_SEARCH, response));
  }
}

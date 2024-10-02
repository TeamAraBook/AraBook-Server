package com.arabook.arabook.api.book.controller;

import static com.arabook.arabook.common.success.book.BookSuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.api.book.service.BookService;
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/search")
  public ResponseEntity<ResponseTemplate<BooksResponse>> getBooksBySearch(
      @RequestParam("keyword") final String keyword) {
    BooksResponse response = bookService.getBooksBySearch(keyword);
    return ResponseEntity.ok(ResponseTemplate.success(GET_BOOKS_BY_SEARCH, response));
  }
}

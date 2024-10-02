package com.arabook.arabook.api.book.service;

import com.arabook.arabook.api.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;

public interface BookService {
  BooksResponse getBooksBySearch(String keyword);

  BookDetailResponse getBookDetail(Long bookId);
}

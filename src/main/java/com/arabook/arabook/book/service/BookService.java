package com.arabook.arabook.book.service;

import com.arabook.arabook.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.book.controller.dto.response.BooksResponse;

public interface BookService {
  BooksResponse getBooksBySearch(String keyword);

  BookDetailResponse getBookDetail(Long bookId);
}

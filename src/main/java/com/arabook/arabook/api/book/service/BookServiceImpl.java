package com.arabook.arabook.api.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.api.book.controller.dto.response.BookResponse;
import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.storage.domain.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  @Override
  public BooksResponse getBooksBySearch(final String keyword) {
    final List<BookResponse> bookResponses = bookRepository.findBooksBySearch(keyword);
    return BooksResponse.of(bookResponses.size(), bookResponses);
  }

  @Override
  public BookDetailResponse getBookDetail(final Long bookId) {
    return bookRepository.findBookDetail(bookId);
  }
}

package com.arabook.arabook.api.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.book.controller.dto.response.BookResponse;
import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.storage.domain.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BestSellerServiceImpl implements BestSellerService {
  private final BookRepository bookRepository;

  @Override
  public BooksResponse getBestSellerBooks() {
    List<BookResponse> response = bookRepository.findBestSellerBooks();
    return BooksResponse.of(response.size(), response);
  }
}

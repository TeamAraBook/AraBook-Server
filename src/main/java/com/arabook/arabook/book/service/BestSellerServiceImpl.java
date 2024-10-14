package com.arabook.arabook.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.book.controller.dto.response.AIRecommendBookResponse;
import com.arabook.arabook.book.controller.dto.response.BookResponse;
import com.arabook.arabook.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.book.repository.BookRepository;

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

  @Override
  public AIRecommendBookResponse getAIRecommendBook(Long memberId) {
    boolean isNotServiceMember = memberId == null;
    if (isNotServiceMember) {
      return bookRepository.findAIRecommendBookDefault();
    }
    return bookRepository.findAIRecommendBook(memberId);
  }
}

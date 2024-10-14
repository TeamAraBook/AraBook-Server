package com.arabook.arabook.book.service;

import com.arabook.arabook.book.controller.dto.response.AIRecommendBookResponse;
import com.arabook.arabook.book.controller.dto.response.BooksResponse;

public interface BestSellerService {
  BooksResponse getBestSellerBooks();

  AIRecommendBookResponse getAIRecommendBook(Long memberId);
}

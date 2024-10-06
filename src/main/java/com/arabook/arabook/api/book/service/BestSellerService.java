package com.arabook.arabook.api.book.service;

import com.arabook.arabook.api.book.controller.dto.response.AIRecommendBookResponse;
import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;

public interface BestSellerService {
  BooksResponse getBestSellerBooks();

  AIRecommendBookResponse getAIRecommendBook(Long memberId);
}

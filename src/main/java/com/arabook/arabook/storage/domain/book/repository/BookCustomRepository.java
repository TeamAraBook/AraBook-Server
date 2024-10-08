package com.arabook.arabook.storage.domain.book.repository;

import java.util.List;

import com.arabook.arabook.api.book.controller.dto.response.AIRecommendBookResponse;
import com.arabook.arabook.api.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.api.book.controller.dto.response.BookResponse;

public interface BookCustomRepository {
  List<BookResponse> findBooksBySearch(String keyword);

  BookDetailResponse findBookDetail(Long bookId);

  List<BookResponse> findBestSellerBooks();

  AIRecommendBookResponse findAIRecommendBookDefault();

  AIRecommendBookResponse findAIRecommendBook(Long memberId);
}

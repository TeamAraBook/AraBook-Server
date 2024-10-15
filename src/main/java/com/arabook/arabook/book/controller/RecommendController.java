package com.arabook.arabook.book.controller;

import static com.arabook.arabook.common.success.book.BookSuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.book.controller.dto.response.AIRecommendBookResponse;
import com.arabook.arabook.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.book.service.BestSellerService;
import com.arabook.arabook.common.response.ResponseTemplate;
import com.arabook.arabook.common.security.AuthMember;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController implements RecommendApi {
  private final BestSellerService bestSellerService;

  @Override
  @GetMapping("/best-seller")
  public ResponseEntity<ResponseTemplate<BooksResponse>> getBestSellerBooks() {
    BooksResponse response = bestSellerService.getBestSellerBooks();
    return ResponseEntity.ok(ResponseTemplate.success(GET_BEST_SELLER_BOOKS, response));
  }

  @Override
  @GetMapping("/ai")
  public ResponseEntity<ResponseTemplate<AIRecommendBookResponse>> getAIRecommendBook(
      @AuthMember final Long memberId) {
    AIRecommendBookResponse response = bestSellerService.getAIRecommendBook(memberId);
    return ResponseEntity.ok(ResponseTemplate.success(GET_AI_BOOK, response));
  }
}

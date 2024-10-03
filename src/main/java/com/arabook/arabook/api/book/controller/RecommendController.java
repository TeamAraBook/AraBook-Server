package com.arabook.arabook.api.book.controller;

import static com.arabook.arabook.common.success.book.BookSuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.api.book.service.BestSellerService;
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {
  private final BestSellerService bestSellerService;

  @GetMapping("/best-seller")
  public ResponseEntity<ResponseTemplate<BooksResponse>> getBestSellerBooks() {
    BooksResponse response = bestSellerService.getBestSellerBooks();
    return ResponseEntity.ok(ResponseTemplate.success(GET_BEST_SELLER_BOOKS, response));
  }
}

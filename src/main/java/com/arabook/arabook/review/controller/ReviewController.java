package com.arabook.arabook.review.controller;

import static com.arabook.arabook.common.success.review.ReviewSuccessType.*;

import java.net.URI;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.common.response.ResponseTemplate;
import com.arabook.arabook.common.security.AuthMember;
import com.arabook.arabook.review.controller.dto.request.CreateReviewRequest;
import com.arabook.arabook.review.controller.dto.request.UpdateReviewRequest;
import com.arabook.arabook.review.controller.dto.response.ReviewDetailResponse;
import com.arabook.arabook.review.controller.dto.response.ReviewIdResponse;
import com.arabook.arabook.review.controller.dto.response.ReviewsResponse;
import com.arabook.arabook.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {
  private final ReviewService reviewService;

  private static final String REVIEW_PATH = "/reviews";

  @Override
  @PostMapping("")
  public ResponseEntity<ResponseTemplate<ReviewIdResponse>> createReview(
      @RequestBody @Valid final CreateReviewRequest request, @AuthMember final Long memberId) {
    ReviewIdResponse response = reviewService.createReview(request, memberId);
    URI uri = URI.create(REVIEW_PATH + "/" + response.reviewId());
    return ResponseEntity.created(uri)
        .body(ResponseTemplate.success(CREATE_REVIEW_SUCCESS, response));
  }

  @Override
  @PutMapping("")
  public ResponseEntity<ResponseTemplate<ReviewIdResponse>> updateReview(
      @RequestBody @Valid UpdateReviewRequest request, @AuthMember final Long memberId) {
    ReviewIdResponse response = reviewService.updateReview(request, memberId);
    return ResponseEntity.ok().body(ResponseTemplate.success(UPDATE_REVIEW_SUCCESS, response));
  }

  @Override
  @GetMapping("")
  public ResponseEntity<ResponseTemplate<ReviewsResponse>> getReviews(
      @AuthMember final Long memberId) {
    ReviewsResponse response = reviewService.getReviews(memberId);
    return ResponseEntity.ok().body(ResponseTemplate.success(GET_REVIEWS_SUCCESS, response));
  }

  @Override
  @DeleteMapping("/{reviewId}")
  public ResponseEntity<ResponseTemplate> deleteReview(
      @PathVariable final Long reviewId, @AuthMember final Long memberId) {
    reviewService.deleteReview(reviewId, memberId);
    return ResponseEntity.ok().body(ResponseTemplate.success(DELETE_REVIEW_SUCCESS));
  }

  @Override
  @GetMapping("/{reviewId}")
  public ResponseEntity<ResponseTemplate<ReviewDetailResponse>> getReviewDetail(
      @PathVariable final Long reviewId, @AuthMember final Long memberId) {
    ReviewDetailResponse response = reviewService.getReviewDetail(reviewId, memberId);
    return ResponseEntity.ok().body(ResponseTemplate.success(GET_REVIEW_DETAIL_SUCCESS, response));
  }
}

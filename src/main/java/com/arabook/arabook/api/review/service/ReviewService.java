package com.arabook.arabook.api.review.service;

import com.arabook.arabook.api.review.controller.dto.request.CreateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.request.UpdateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.response.ReviewDetailResponse;
import com.arabook.arabook.api.review.controller.dto.response.ReviewIdResponse;
import com.arabook.arabook.api.review.controller.dto.response.ReviewsResponse;

public interface ReviewService {
  ReviewIdResponse createReview(CreateReviewRequest request, Long memberId);

  ReviewIdResponse updateReview(UpdateReviewRequest request, Long memberId);

  ReviewsResponse getReviews(Long memberId);

  void deleteReview(Long reviewId, Long memberId);

  ReviewDetailResponse getReviewDetail(Long reviewId, Long memberId);
}

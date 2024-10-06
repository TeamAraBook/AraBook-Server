package com.arabook.arabook.storage.domain.review.repository;

import java.util.List;

import com.arabook.arabook.api.review.controller.dto.response.ReviewDetailResponse;
import com.arabook.arabook.api.review.controller.dto.response.ReviewResponse;

public interface ReviewCustomRepository {
  List<ReviewResponse> findReviewsByReviewerId(Long memberId);

  ReviewDetailResponse findReviewDetailByReviewIdAndReviewerId(Long reviewId, Long memberId);
}

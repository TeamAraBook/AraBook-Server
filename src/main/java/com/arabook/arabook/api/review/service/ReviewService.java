package com.arabook.arabook.api.review.service;

import com.arabook.arabook.api.review.controller.dto.request.CreateReviewRequest;

public interface ReviewService {
  Long createReview(CreateReviewRequest request, Long memberId);
}

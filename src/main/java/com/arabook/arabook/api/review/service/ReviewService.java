package com.arabook.arabook.api.review.service;

import com.arabook.arabook.api.review.controller.dto.request.CreateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.request.UpdateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.response.ReviewIdResponse;

public interface ReviewService {
  ReviewIdResponse createReview(CreateReviewRequest request, Long memberId);

  ReviewIdResponse updateReview(UpdateReviewRequest request, Long memberId);
}

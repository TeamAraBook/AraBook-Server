package com.arabook.arabook.api.review.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ReviewIdResponse", description = "기록 id 조회 DTO")
public record ReviewIdResponse(@Schema(description = "기록 id", example = "1") Long reviewId) {
  public static ReviewIdResponse of(Long reviewId) {
    return new ReviewIdResponse(reviewId);
  }
}

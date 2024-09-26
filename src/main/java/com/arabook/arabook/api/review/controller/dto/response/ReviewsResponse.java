package com.arabook.arabook.api.review.controller.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ReviewsResponse", description = "책 기록 리스트 응답 DTO")
public record ReviewsResponse(
		@Schema(description = "조회된 기록의 수", example = "1") int totalCount,
		@Schema(description = "기록 목록") List<ReviewResponse> reviews) {}

package com.arabook.arabook.api.review.controller.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateReviewRequest", description = "책에 대한 기록 수정 요청 DTO")
public record UpdateReviewRequest(
		@NotNull @Schema(description = "기록 id", example = "1") Long reviewId,
		@NotNull
				@Schema(
						description =
								"리뷰 태그(DISAPPOINTED,SLIGHTLY_DISAPPOINTED,AVERAGE,ENJOYABLE,LIFE_CHANGING 중 1",
						example = "1")
				String reviewTag,
		@NotNull @Schema(description = "기록 내용", example = "재밌었음") String content,
		@NotNull @Schema(description = "책을 읽기 시작한 날", example = "2024-09-21") LocalDate readStartDate,
		@NotNull @Schema(description = "책을 다 읽은 날", example = "2024-09-24") LocalDate readEndDate) {}

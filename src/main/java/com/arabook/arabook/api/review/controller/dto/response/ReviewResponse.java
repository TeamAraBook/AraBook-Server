package com.arabook.arabook.api.review.controller.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ReviewResponse", description = "책에 대한 기록 조회 DTO")
public record ReviewResponse(
		@Schema(description = "기록 id", example = "1") long reviewId,
		@Schema(description = "책 표지 url", example = "https://images/1") String coverUrl,
		@Schema(description = "책 제목", example = "퀸의 대각선") String title,
		@Schema(description = "책을 읽는데 소요된 기간", example = "4") int readPeriod,
		@Schema(description = "책을 읽기 시작한 날", example = "2024-09-21") LocalDate readStartDate,
		@Schema(description = "책을 다 읽은 날", example = "2024-09-24") LocalDate readEndDate,
		@Schema(description = "기록 캐릭터 icon", example = "https://icon/1") String reviewTagIcon,
		@Schema(description = "기록 캐릭터 color", example = "1FD068") String reviewTagColor) {}

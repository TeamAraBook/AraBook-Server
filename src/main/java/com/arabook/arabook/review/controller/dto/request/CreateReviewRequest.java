package com.arabook.arabook.review.controller.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.review.entity.enums.ReviewTag;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateReviewRequest", description = "책에 대한 기록 생성 요청 DTO")
public record CreateReviewRequest(
    @NotNull @Schema(description = "책 id", example = "1") Long bookId,
    @NotNull
        @Schema(
            description =
                "리뷰 태그(DISAPPOINTED,SLIGHTLY_DISAPPOINTED,AVERAGE,ENJOYABLE,LIFE_CHANGING 중 1",
            example = "DISAPPOINTED")
        ReviewTag reviewTag,
    @NotNull @Schema(description = "기록 내용", example = "재밌었음") String content,
    @NotNull @Schema(description = "책을 읽기 시작한 날", example = "2024-09-21") LocalDate readStartDate,
    @NotNull @Schema(description = "책을 다 읽은 날", example = "2024-09-24") LocalDate readEndDate) {}

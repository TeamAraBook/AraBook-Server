package com.arabook.arabook.api.review.controller.dto.response;

import java.time.LocalDate;

import com.arabook.arabook.storage.domain.review.entity.Review;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ReviewDetailResponse", description = "책에 대한 기록 상세 조회 DTO")
public record ReviewDetailResponse(
    @Schema(description = "기록 id", example = "1") long reviewId,
    @Schema(description = "책 id", example = "1") long bookId,
    @Schema(description = "책을 읽기 시작한 날", example = "2024-09-21") LocalDate readStartDate,
    @Schema(description = "책을 다 읽은 날", example = "2024-09-24") LocalDate readEndDate,
    @Schema(description = "책 표지 url", example = "https://images/1") String coverUrl,
    @Schema(description = "책 제목", example = "퀸의 대각선") String title,
    @Schema(description = "책 작가", example = "베르나르베르베르") String author,
    @Schema(description = "기록 캐릭터 icon", example = "https://icon/1") String reviewTagIcon,
    @Schema(description = "기록 캐릭터 comment", example = "조금 아쉬웠어") String reviewTagComment,
    @Schema(description = "기록 캐릭터 color", example = "1FD068") String reviewTagColor,
    @Schema(description = "기록 내용", example = "재밌었음") String content) {
  public static ReviewDetailResponse from(Review review) {
    return new ReviewDetailResponse(
        review.getReviewId(),
        review.getBook().getBookId(),
        review.getReadStartDate(),
        review.getReadEndDate(),
        review.getBook().getCoverUrl(),
        review.getBook().getTitle(),
        review.getBook().getAuthor(),
        review.getReviewTag().getReviewTagIcon(),
        review.getReviewTag().getReviewTagComment(),
        review.getReviewTag().getReviewTagColor(),
        review.getContent());
  }
}

package com.arabook.arabook.review.controller;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.common.response.ResponseTemplate;
import com.arabook.arabook.review.controller.dto.request.CreateReviewRequest;
import com.arabook.arabook.review.controller.dto.request.UpdateReviewRequest;
import com.arabook.arabook.review.controller.dto.response.ReviewDetailResponse;
import com.arabook.arabook.review.controller.dto.response.ReviewIdResponse;
import com.arabook.arabook.review.controller.dto.response.ReviewsResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "책 기록 관련 API")
public interface ReviewApi {
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "기록이 생성되었습니다."),
        @ApiResponse(
            responseCode = "400",
            description = "책 읽기 시작 날짜가 올바르지 않습니다. (책을 읽기 시작한 날짜가 다 읽은 날짜보다 늦을 경우)"),
        @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다. (넘기는 필드가 조건에 부합하지 않는 경우"),
        @ApiResponse(responseCode = "404", description = "해당 책을 찾을 수 없습니다.")
      })
  @Operation(summary = "책 기록하기: 책 기록 생성 요청", description = "책 기록을 생성합니다.")
  ResponseEntity<ResponseTemplate<ReviewIdResponse>> createReview(
      CreateReviewRequest request, @Schema(hidden = true) Long memberId);

  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "기록을 조회했습니다."),
        @ApiResponse(responseCode = "404", description = "기록을 찾을 수 없습니다.")
      })
  @Operation(summary = "책 기록하기: 책 기록 조회", description = "책 기록을 조회합니다.")
  ResponseEntity<ResponseTemplate<ReviewDetailResponse>> getReviewDetail(
      Long reviewId, @Schema(hidden = true) Long memberId);

  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "기록을 수정했습니다."),
        @ApiResponse(
            responseCode = "400",
            description = "책 읽기 시작 날짜가 올바르지 않습니다. (책을 읽기 시작한 날짜가 다 읽은 날짜보다 늦을 경우)"),
        @ApiResponse(responseCode = "400", description = "입력값이 올바르지 않습니다. (넘기는 필드가 조건에 부합하지 않는 경우"),
        @ApiResponse(responseCode = "404", description = "기록을 찾을 수 없습니다.")
      })
  @Operation(summary = "책 기록하기: 책 기록 수정 요청", description = "책 기록을 수정합니다.")
  ResponseEntity<ResponseTemplate<ReviewIdResponse>> updateReview(
      UpdateReviewRequest request, @Schema(hidden = true) Long memberId);

  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "기록을 삭제했습니다."),
        @ApiResponse(responseCode = "404", description = "기록을 찾을 수 없습니다.")
      })
  @Operation(summary = "책 기록하기: 책 기록 삭제 요청", description = "책 기록을 삭제합니다.")
  ResponseEntity<ResponseTemplate> deleteReview(
      Long reviewId, @Schema(hidden = true) Long memberId);

  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "기록 목록 조회했습니다.")})
  @Operation(summary = "책 기록하기: 책 기록 목록 조회", description = "책 기록 목록을 조회합니다.")
  ResponseEntity<ResponseTemplate<ReviewsResponse>> getReviews(
      @Schema(hidden = true) Long memberId);
}

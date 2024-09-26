package com.arabook.arabook.api.review.controller;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.api.review.controller.dto.request.CreateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.request.UpdateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.response.ReviewDetailResponse;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "책 기록 관련 API")
public interface ReviewApi {
	@ApiResponses(
			value = {
				@ApiResponse(responseCode = "201", description = "기록이 생성되었습니다."),
				@ApiResponse(responseCode = "400", description = "기록 생성 요청이 잘못되었습니다."),
				@ApiResponse(responseCode = "404", description = "해당 책을 찾을 수 없습니다.")
			})
	@Operation(summary = "책 기록하기: 책 기록 생성 요청", description = "책 기록을 생성합니다.")
	ResponseEntity<ResponseTemplate> createReview(CreateReviewRequest request);

	@ApiResponses(
			value = {
				@ApiResponse(responseCode = "200", description = "기록을 조회했습니다."),
				@ApiResponse(responseCode = "404", description = "기록을 찾을 수 없습니다.")
			})
	@Operation(summary = "책 기록하기: 책 기록 조회", description = "책 기록을 조회합니다.")
	ResponseEntity<ResponseTemplate<ReviewDetailResponse>> getReviewDetail(Long reviewId);

	@ApiResponses(
			value = {
				@ApiResponse(responseCode = "200", description = "기록을 수정했습니다."),
				@ApiResponse(responseCode = "400", description = "기록 수정 요청이 잘못되었습니다."),
				@ApiResponse(responseCode = "404", description = "기록을 찾을 수 없습니다.")
			})
	@Operation(summary = "책 기록하기: 책 기록 수정 요청", description = "책 기록을 수정합니다.")
	ResponseEntity<ResponseTemplate> updateReview(UpdateReviewRequest request);
}

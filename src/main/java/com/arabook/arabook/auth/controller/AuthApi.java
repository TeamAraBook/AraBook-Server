package com.arabook.arabook.auth.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.auth.controller.dto.request.AuthRequest;
import com.arabook.arabook.auth.controller.dto.response.AuthResponse;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "auth", description = "인증과 관련된 API")
public interface AuthApi {
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "소셜 로그인에 성공했습니다",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{\"code\": 200, \"message\": \"온보딩을 완료했습니다.\", \"data\": {\"memberId\": 2, \"role\": \"GUEST\", \"token\": {\"accessToken\": \"accessToken\", \"refreshToken\": \"refreshToken\"}}}"))),
        @ApiResponse(
            responseCode = "400",
            description = "소셜 로그인에 실패했습니다",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{\"code\": 400, \"message\": \"올바르지 않은 소셜 로그인 요청입니다.\", \"data\": \"No Data\"}")))
      })
  @Operation(summary = "온보딩: 카테고리 리스트 조회", description = "카테고리를 조회합니다.")
  ResponseEntity<ResponseTemplate<AuthResponse>> signUpOrLogin(@Valid AuthRequest request);
}

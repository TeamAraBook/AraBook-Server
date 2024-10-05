package com.arabook.arabook.api.member.controller;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.api.member.controller.dto.request.MemberOnboardingRequest;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "member", description = "회원과 관련된 API")
public interface MemberApi {
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "204",
            description = "온보딩을 완료했습니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{ \"code\": 204, \"message\": \"온보딩을 완료했습니다.\", \"data\": \"No Data\" }"))),
        @ApiResponse(
            responseCode = "400",
            description = "유효한 카테고리를 찾을 수 없습니다",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{ \"code\": 400, \"message\": \"유효한 카테고리를 찾을 수 없습니다.\", \"data\": \"No Data\" }")))
      })
  @Operation(summary = "온보딩: 회원가입 이후 온보딩 요청", description = "온보딩 정보를 받습니다")
  ResponseEntity<ResponseTemplate> onboarding(
      MemberOnboardingRequest request, @Schema(hidden = true) Long memberId);
}

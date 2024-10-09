package com.arabook.arabook.api.auth.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "IssueTokenResponse", description = "토큰 응답 DTO")
public record IssueTokenResponse(
    @Schema(description = "액세스 토큰") String accessToken,
    @Schema(description = "리프레시 토큰") String refreshToken) {
  public static IssueTokenResponse of(String accessToken, String refreshToken) {
    return new IssueTokenResponse(accessToken, refreshToken);
  }
}

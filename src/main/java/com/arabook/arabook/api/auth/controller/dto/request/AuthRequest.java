package com.arabook.arabook.api.auth.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.member.entity.enums.SocialPlatformType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AuthRequest", description = "소셜 로그인 요청 DTO")
public record AuthRequest(
    @Schema(description = "소셜 플랫폼", example = "KAKAO/APPLE") @NotNull
        SocialPlatformType platformType,
    @Schema(description = "소셜 플랫폼에서 받은 토큰", example = "TOKEN") @NotNull String socialToken) {}

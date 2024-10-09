package com.arabook.arabook.api.auth.controller.dto.response;

import com.arabook.arabook.storage.domain.member.entity.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AuthResponse", description = "소셜 로그인 응답 DTO")
public record AuthResponse(
    @Schema(description = "회원 id", example = "1") Long memberId,
    @Schema(description = "회원 권한", example = "GUEST or MEMBER") Role role,
    @Schema(description = "토큰 정보") IssueTokenResponse token) {}

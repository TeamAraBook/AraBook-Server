package com.arabook.arabook.api.member.controller.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.storage.domain.member.entity.enums.Gender;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MemberOnboardingRequest", description = "회원 온보딩 요청 DTO")
public record MemberOnboardingRequest(
    @Schema(description = "성별 (MAN, WOMAN, UNKNOWN", example = "WOMAN") @NotNull Gender gender,
    @Schema(description = "나이", example = "21") int age,
    @Schema(description = "서버로부터 조회한 category id 리스트", example = "[1,2,3]") @NotNull
        List<Long> interestCategoryIds) {}

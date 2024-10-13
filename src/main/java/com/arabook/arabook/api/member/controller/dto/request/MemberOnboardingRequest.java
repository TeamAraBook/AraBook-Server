package com.arabook.arabook.api.member.controller.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.storage.domain.member.entity.enums.Gender;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MemberOnboardingRequest", description = "회원 온보딩 요청 DTO")
public record MemberOnboardingRequest(
    @Schema(description = "회원의 별명", example = "으나") @NotNull String nickname,
    @Schema(description = "성별 (MAN, WOMAN, UNKNOWN", example = "WOMAN") @NotNull Gender gender,
    @Schema(description = "태어난 년도, 선택하지 않은 경우에는 null", example = "2001") String birthYear,
    @Schema(description = "서버로부터 조회한 sub category id 리스트", example = "[1,2,3]") @NotNull
        List<Long> interestSubCategoryIds) {}

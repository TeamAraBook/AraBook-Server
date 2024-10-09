package com.arabook.arabook.api.auth.service.vo;

import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.storage.domain.member.entity.Member;
import com.arabook.arabook.storage.domain.member.entity.enums.SocialPlatformType;

public record AuthMemberVO(
    @NotNull String platformId, @NotNull SocialPlatformType platformType, String email) {
  public static AuthMemberVO of(String platformId, SocialPlatformType platformType) {
    return new AuthMemberVO(platformId, platformType, null);
  }

  public static AuthMemberVO of(String platformId, SocialPlatformType platformType, String email) {
    return new AuthMemberVO(platformId, platformType, email);
  }

  public Member toEntity() {
    return Member.builder().socialPlatformId(platformId).socialPlatformType(platformType).build();
  }
}

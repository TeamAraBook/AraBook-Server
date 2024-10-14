package com.arabook.arabook.member.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.member.entity.enums.Gender;
import com.arabook.arabook.member.entity.enums.Role;
import com.arabook.arabook.member.entity.enums.SocialPlatformType;
import com.arabook.arabook.storage.domain.common.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "members")
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private SocialPlatformType socialPlatformType;

  @NotNull
  @Column(unique = true)
  private String socialPlatformId;

  private String email;

  private String nickname;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private int age;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  private Member(SocialPlatformType socialPlatformType, String socialPlatformId, String email) {
    this.socialPlatformType = socialPlatformType;
    this.socialPlatformId = socialPlatformId;
    this.email = email;
    this.gender = Gender.UNKNOWN;
    this.age = 999;
    this.role = Role.GUEST;
  }

  public void updateOnboardingInfo(final String nickname, final Gender gender, final int age) {
    this.nickname = nickname;
    this.gender = gender;
    this.age = age;
  }

  public int calculateAge(final String birthYear) {
    if (birthYear == null) {
      return 999;
    }
    return LocalDateTime.now().getYear() - Integer.parseInt(birthYear);
  }
}

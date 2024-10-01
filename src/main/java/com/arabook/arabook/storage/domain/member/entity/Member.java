package com.arabook.arabook.storage.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.storage.domain.common.entity.BaseTimeEntity;
import com.arabook.arabook.storage.domain.member.entity.enums.Gender;
import com.arabook.arabook.storage.domain.member.entity.enums.Role;
import com.arabook.arabook.storage.domain.member.entity.enums.SocialPlatformType;

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

	@NotNull
	@Column(unique = true)
	private String email;

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
}

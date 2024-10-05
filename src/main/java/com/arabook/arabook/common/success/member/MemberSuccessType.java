package com.arabook.arabook.common.success.member;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.success.common.SuccessType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberSuccessType implements SuccessType {
  ONBOARDING_SUCCESS(HttpStatus.OK, "온보딩을 완료했습니다.");

  private final HttpStatus status;
  private final String message;

  @Override
  public HttpStatus status() {
    return this.status;
  }

  @Override
  public String message() {
    return this.message;
  }
}

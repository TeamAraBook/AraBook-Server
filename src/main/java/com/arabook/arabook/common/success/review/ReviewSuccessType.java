package com.arabook.arabook.common.success.review;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.success.common.SuccessType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewSuccessType implements SuccessType {
  CREATE_REVIEW_SUCCESS(HttpStatus.CREATED, "기록이 생성되었습니다");

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

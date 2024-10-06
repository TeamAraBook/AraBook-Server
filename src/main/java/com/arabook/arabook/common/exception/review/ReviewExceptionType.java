package com.arabook.arabook.common.exception.review;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.exception.common.ExceptionType;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReviewExceptionType implements ExceptionType {
  INVALID_READ_START_DATE(HttpStatus.BAD_REQUEST, "책 읽기 시작 날짜가 올바르지 않습니다."),
  NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "기록을 찾을 수 없습니다");

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

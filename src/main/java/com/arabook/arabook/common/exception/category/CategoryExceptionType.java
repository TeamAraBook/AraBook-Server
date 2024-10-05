package com.arabook.arabook.common.exception.category;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.exception.common.ExceptionType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CategoryExceptionType implements ExceptionType {
  INVALID_CATEGORY_ID(HttpStatus.BAD_REQUEST, "유효한 카테고리를 찾을 수 없습니다");

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

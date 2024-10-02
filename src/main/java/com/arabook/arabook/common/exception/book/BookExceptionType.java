package com.arabook.arabook.common.exception.book;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.exception.common.ExceptionType;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BookExceptionType implements ExceptionType {
  INVALID_BOOK_ISBN(HttpStatus.BAD_REQUEST, "ISBN은 13자리여야 합니다");

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

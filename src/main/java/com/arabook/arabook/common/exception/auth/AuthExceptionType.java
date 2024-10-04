package com.arabook.arabook.common.exception.auth;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.exception.common.ExceptionType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthExceptionType implements ExceptionType {
  INVALID_TOKEN(HttpStatus.BAD_REQUEST, "서비스에서 발급되지 않은 토큰입니다."),
  UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "기한이 만료된 토큰입니다."),
  INVALID_REQUEST_LOGIN(HttpStatus.BAD_REQUEST, "올바르지 않은 로그인 요청입니다");

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

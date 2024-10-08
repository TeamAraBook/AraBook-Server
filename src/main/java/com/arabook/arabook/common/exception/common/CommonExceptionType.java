package com.arabook.arabook.common.exception.common;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommonExceptionType implements ExceptionType {
  // 400 Bad Request
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다"),
  INVALID_REQUEST_PARAM_TYPE(HttpStatus.BAD_REQUEST, "요청한 파라미터 타입이 올바르지 않습니다"),
  NOT_NULL_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다"),
  // 404 Not Found
  NOT_FOUND_PATH(HttpStatus.NOT_FOUND, "요청하신 경로를 찾을 수 없습니다"),
  // 415 UNSUPPORTED_MEDIA_TYPE
  INVALID_JSON_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "올바른 요청 형식이 아닙니다"),
  // 500 Internal Server Error
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

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

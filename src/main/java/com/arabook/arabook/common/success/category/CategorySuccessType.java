package com.arabook.arabook.common.success.category;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.success.common.SuccessType;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CategorySuccessType implements SuccessType {
  // 200 OK
  GET_MAIN_CATEGORIES(HttpStatus.OK, "대분류 카테고리 조회에 성공했습니다");

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

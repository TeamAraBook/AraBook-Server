package com.arabook.arabook.common.success.book;

import org.springframework.http.HttpStatus;

import com.arabook.arabook.common.success.common.SuccessType;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BookSuccessType implements SuccessType {
  // 200 OK
  GET_BOOKS_BY_SEARCH(HttpStatus.OK, "검색 결과를 조회하였습니다"),
  GET_BOOK_DETAIL(HttpStatus.OK, "책 상세 정보를 조회하였습니다");

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

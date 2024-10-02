package com.arabook.arabook.common.exception.common;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private final ExceptionType exceptionType;

  public BusinessException(ExceptionType exceptionType) {
    super(exceptionType.message());
    this.exceptionType = exceptionType;
  }
}

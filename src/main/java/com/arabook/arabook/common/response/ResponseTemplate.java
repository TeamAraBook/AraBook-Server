package com.arabook.arabook.common.response;

import com.arabook.arabook.common.exception.common.ExceptionType;
import com.arabook.arabook.common.success.common.SuccessType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseTemplate<T> {
  private static final String DEFAULT_DATA = "No Data";

  private final int code;
  private final String message;
  private T data;

  public static ResponseTemplate success(SuccessType type) {
    return new ResponseTemplate<>(type.status().value(), type.message(), DEFAULT_DATA);
  }

  public static <T> ResponseTemplate<T> success(SuccessType type, T data) {
    return new ResponseTemplate<>(type.status().value(), type.message(), data);
  }

  public static ResponseTemplate error(ExceptionType type) {
    return new ResponseTemplate<>(type.status().value(), type.message(), DEFAULT_DATA);
  }
}

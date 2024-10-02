package com.arabook.arabook.common.exception.review;

import com.arabook.arabook.common.exception.common.ClientException;
import com.arabook.arabook.common.exception.common.ExceptionType;

public class ReviewException extends ClientException {
  public ReviewException(ExceptionType exceptionType) {
    super(exceptionType);
  }
}

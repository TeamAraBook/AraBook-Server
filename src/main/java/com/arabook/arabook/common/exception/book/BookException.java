package com.arabook.arabook.common.exception.book;

import com.arabook.arabook.common.exception.common.ClientException;
import com.arabook.arabook.common.exception.common.ExceptionType;

public class BookException extends ClientException {
  public BookException(ExceptionType exceptionType) {
    super(exceptionType);
  }
}

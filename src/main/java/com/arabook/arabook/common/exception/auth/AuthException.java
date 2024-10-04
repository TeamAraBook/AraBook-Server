package com.arabook.arabook.common.exception.auth;

import com.arabook.arabook.common.exception.common.ClientException;
import com.arabook.arabook.common.exception.common.ExceptionType;

public class AuthException extends ClientException {
  public AuthException(ExceptionType exceptionType) {
    super(exceptionType);
  }
}

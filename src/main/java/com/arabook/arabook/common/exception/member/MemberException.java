package com.arabook.arabook.common.exception.member;

import com.arabook.arabook.common.exception.common.ClientException;
import com.arabook.arabook.common.exception.common.ExceptionType;

public class MemberException extends ClientException {
  public MemberException(ExceptionType exceptionType) {
    super(exceptionType);
  }
}

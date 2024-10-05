package com.arabook.arabook.common.exception.category;

import com.arabook.arabook.common.exception.common.ClientException;
import com.arabook.arabook.common.exception.common.ExceptionType;

public class CategoryException extends ClientException {
  public CategoryException(ExceptionType exceptionType) {
    super(exceptionType);
  }
}

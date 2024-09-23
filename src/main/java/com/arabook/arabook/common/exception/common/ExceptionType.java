package com.arabook.arabook.common.exception.common;

import org.springframework.http.HttpStatus;

public interface ExceptionType {
	HttpStatus status();

	String message();
}

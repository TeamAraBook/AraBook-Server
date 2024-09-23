package com.arabook.arabook.common.success.common;

import org.springframework.http.HttpStatus;

public interface SuccessType {
	HttpStatus status();

	String message();
}

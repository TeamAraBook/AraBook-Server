package com.arabook.arabook.common.response;

import com.arabook.arabook.common.exception.common.ExceptionType;
import com.arabook.arabook.common.success.common.SuccessType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResponseTemplate<T> {
	private final int code;
	private final String message;
	private T data;

	public static ResponseTemplate success(SuccessType type) {
		return new ResponseTemplate<>(type.status().value(), type.message());
	}

	public static <T> ResponseTemplate<T> success(SuccessType type, T data) {
		return new ResponseTemplate<T>(type.status().value(), type.message(), data);
	}

	public static ResponseTemplate error(ExceptionType type) {
		return new ResponseTemplate<>(type.status().value(), type.message());
	}
}

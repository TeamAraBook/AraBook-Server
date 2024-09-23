package com.arabook.arabook.api.global.handler;

import static com.arabook.arabook.common.exception.common.CommonExceptionType.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.arabook.arabook.common.exception.common.BusinessException;
import com.arabook.arabook.common.exception.common.ClientException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<String> handleCustomException(ClientException ex) {
		return ResponseEntity.status(ex.getExceptionType().status()).body(ex.getMessage());
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleCustomException(BusinessException ex) {
		log.error(
				"🚨BusinessException occurred: {} 🚨\n{}", ex.getMessage(), getStackTraceAsString(ex));
		return ResponseEntity.status(ex.getExceptionType().status()).body(ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleServerException(RuntimeException ex) {
		log.error(
				"🚨 InternalException occurred: {} 🚨\n{}", ex.getMessage(), getStackTraceAsString(ex));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(INTERNAL_SERVER_ERROR.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NoHandlerFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(NOT_FOUND_PATH.getMessage() + ": " + ex.getRequestURL());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_INPUT_VALUE.getMessage());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(INVALID_REQUEST_PARAM_TYPE.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(NOT_NULL_REQUEST_PARAM.getMessage());
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException ex) {
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(INVALID_JSON_TYPE.getMessage());
	}

	private String getStackTraceAsString(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}
}

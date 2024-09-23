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
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ResponseTemplate> handleCustomException(ClientException ex) {
		return ResponseEntity.status(ex.getExceptionType().status())
				.body(ResponseTemplate.error(ex.getExceptionType()));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseTemplate> handleCustomException(BusinessException ex) {
		log.error(
				"🚨BusinessException occurred: {} 🚨\n{}", ex.getMessage(), getStackTraceAsString(ex));
		return ResponseEntity.status(ex.getExceptionType().status())
				.body(ResponseTemplate.error(ex.getExceptionType()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseTemplate> handleServerException(RuntimeException ex) {
		log.error(
				"🚨 InternalException occurred: {} 🚨\n{}", ex.getMessage(), getStackTraceAsString(ex));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ResponseTemplate.error(INTERNAL_SERVER_ERROR));
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ResponseTemplate> handleNotFoundException(NoHandlerFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.error(NOT_FOUND_PATH));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseTemplate> handleValidationError(
			MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseTemplate.error(INVALID_INPUT_VALUE));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseTemplate> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseTemplate.error(INVALID_REQUEST_PARAM_TYPE));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseTemplate> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseTemplate.error(NOT_NULL_REQUEST_PARAM));
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ResponseTemplate> handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException ex) {
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(ResponseTemplate.error(INVALID_JSON_TYPE));
	}

	private String getStackTraceAsString(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}
}

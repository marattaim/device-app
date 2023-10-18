package com.example.testdevice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class DeviceExceptionHandler {

	@ExceptionHandler(value = {UserNotFound.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorBody userNotFoundException(UserNotFound ex) {
		log.error(ex.getMessage());
		return new ErrorBody(ex.getMessage());
	}

	@ExceptionHandler(value = {DeviceException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorBody deviceException(DeviceException ex) {
		log.error(ex.getMessage());
		return new ErrorBody(ex.getMessage());
	}

	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorBody exceptionHandler(Exception ex) {
		log.error("Unexpected error", ex);
		return new ErrorBody("Oops, something went wrong.");
	}

}

package com.example.testdevice.exception;

import static java.lang.String.format;

public class UserNotFound extends RuntimeException {

	private static final String ERROR_MESSAGE = "User with id [%s] not found";

	public UserNotFound(Long userId) {
		super(format(ERROR_MESSAGE, userId));
	}

}

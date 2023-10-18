package com.example.testdevice.service;

import com.example.testdevice.domain.User;

public interface UserService {

	/**
	 * @param userId to find the user
	 * @return user
	 */
	User findById(Long userId);

}

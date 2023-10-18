package com.example.testdevice.service.impl;

import org.springframework.stereotype.Service;

import com.example.testdevice.domain.User;
import com.example.testdevice.exception.UserNotFound;
import com.example.testdevice.repository.UserRepository;
import com.example.testdevice.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository repository;

	public User findById(Long userId) {
		log.debug("Try to find user by id {}", userId);
		return repository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
	}

}

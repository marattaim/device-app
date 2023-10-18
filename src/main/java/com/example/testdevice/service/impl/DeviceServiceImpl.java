package com.example.testdevice.service.impl;

import org.springframework.stereotype.Service;

import com.example.testdevice.domain.Device;
import com.example.testdevice.exception.DeviceException;
import com.example.testdevice.repository.DeviceRepository;
import com.example.testdevice.service.DeviceService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

	private static final String DEVICE_NOT_FOUND_MESSAGE = "Device not found";

	private final DeviceRepository repository;

	public Device findById(Long deviceId) {
		log.debug("Try to find device by id {}", deviceId);
		return repository.findById(deviceId).orElseThrow(() -> new DeviceException(DEVICE_NOT_FOUND_MESSAGE));
	}

}

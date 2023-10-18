package com.example.testdevice.service;

import com.example.testdevice.domain.Device;

public interface DeviceService {

	/**
	 * @param deviceId to find the device
	 * @return device
	 */
	Device findById(Long deviceId);

}

package com.example.testdevice.service;

import com.example.testdevice.dto.DeviceOrderRequestDTO;
import com.example.testdevice.dto.DeviceOrderResponseDTO;

public interface DeviceOrderService {

	/**
	 * Method used to book device
	 *
	 * @param requestDto contains the user and device for booking
	 * @return success message or throw an exception
	 */
	DeviceOrderResponseDTO orderDevice(DeviceOrderRequestDTO requestDto);

	/**
	 * Method used to release device
	 *
	 * @param requestDto contains user and device to release
	 * @return success message or throw an exception
	 */
	DeviceOrderResponseDTO releaseDevice(DeviceOrderRequestDTO requestDto);

}

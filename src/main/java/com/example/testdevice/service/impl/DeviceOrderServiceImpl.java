package com.example.testdevice.service.impl;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.testdevice.domain.Device;
import com.example.testdevice.domain.DeviceStatus;
import com.example.testdevice.domain.Order;
import com.example.testdevice.domain.User;
import com.example.testdevice.dto.DeviceOrderRequestDTO;
import com.example.testdevice.dto.DeviceOrderResponseDTO;
import com.example.testdevice.exception.DeviceException;
import com.example.testdevice.mapper.DeviceOrderMapper;
import com.example.testdevice.mapper.OrderMapper;
import com.example.testdevice.service.DeviceOrderService;
import com.example.testdevice.service.DeviceService;
import com.example.testdevice.service.OrderService;
import com.example.testdevice.service.UserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class DeviceOrderServiceImpl implements DeviceOrderService {

	private static final String DEVICE_BOOKED_MESSAGE = "Device was booked";
	private static final String DEVICE_RELEASED_MESSAGE = "Device was released";
	private static final String DEVICE_BOOKED_BY_MESSAGE = "The {} device requested by user {} is already booked.";

	private DeviceService deviceService;
	private UserService userService;
	private OrderService orderService;
	private DeviceOrderMapper deviceOrderMapper;
	private OrderMapper orderMapper;

	@Override
	@Transactional
	public DeviceOrderResponseDTO orderDevice(DeviceOrderRequestDTO requestDto) {
		log.info("User {} try to book the device {}", requestDto.userId(), requestDto.deviceId());
		Device device = deviceService.findById(requestDto.deviceId());
		switch (device.getStatus()) {
			case AVAILABLE -> {
				try {
					User user = userService.findById(requestDto.userId());
					device.setStatus(DeviceStatus.BOOKED);
					Order order = orderMapper.toOrder(user, device);
					orderService.create(order);
				} catch (OptimisticLockingFailureException exception) {
					throw new DeviceException(DEVICE_BOOKED_MESSAGE);
				}
			}
			case BOOKED -> {
				log.debug(DEVICE_BOOKED_BY_MESSAGE, device.getName(), requestDto.userId());
				throw new DeviceException(DEVICE_BOOKED_MESSAGE);
			}
		}
		log.info("The Device {} was booked by {}", device.getName(), requestDto.userId());
		return deviceOrderMapper.toResponseDto(DEVICE_BOOKED_MESSAGE);
	}

	@Override
	@Transactional
	public DeviceOrderResponseDTO releaseDevice(DeviceOrderRequestDTO requestDto) {
		log.info("User {} try to release device {}", requestDto.userId(), requestDto.deviceId());
		Device device = deviceService.findById(requestDto.deviceId());
		device.setStatus(DeviceStatus.AVAILABLE);
		return deviceOrderMapper.toResponseDto(DEVICE_RELEASED_MESSAGE);
	}

}

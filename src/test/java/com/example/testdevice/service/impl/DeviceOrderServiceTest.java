package com.example.testdevice.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.testdevice.domain.Device;
import com.example.testdevice.domain.DeviceStatus;
import com.example.testdevice.domain.Order;
import com.example.testdevice.domain.User;
import com.example.testdevice.dto.DeviceOrderRequestDTO;
import com.example.testdevice.dto.DeviceOrderResponseDTO;
import com.example.testdevice.exception.DeviceException;
import com.example.testdevice.mapper.DeviceOrderMapper;
import com.example.testdevice.mapper.OrderMapper;
import com.example.testdevice.service.DeviceService;
import com.example.testdevice.service.OrderService;
import com.example.testdevice.service.UserService;

@ExtendWith(MockitoExtension.class)
class DeviceOrderServiceTest {

	private static final Long AVAILABLE_DEVICE_ID = 1L;
	private static final Long BOOKED_DEVICE_ID = 2L;
	private static final Long USER_ID = 1L;

	@Mock
	private DeviceService deviceService;
	@Mock
	private UserService userService;
	@Mock
	private OrderService orderService;
	@Spy
	private DeviceOrderMapper deviceOrderMapper = Mappers.getMapper(DeviceOrderMapper.class);
	@Spy
	private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
	@InjectMocks
	private DeviceOrderServiceImpl service;

	@Test
	@DisplayName("when the device is available then book the device and return message")
	void shouldBookAvailableDevice() {
		//GIVEN
		when(deviceService.findById(AVAILABLE_DEVICE_ID)).thenReturn(getAvailable());
		when(userService.findById(USER_ID)).thenReturn(new User());
		var orderCaptor = ArgumentCaptor.forClass(Order.class);
		doNothing().when(orderService).create(orderCaptor.capture());
		//WHEN
		DeviceOrderResponseDTO response = service.orderDevice(new DeviceOrderRequestDTO(USER_ID, AVAILABLE_DEVICE_ID));
		//THEN
		verify(userService).findById(USER_ID);
		verify(orderService).create(any());
		assertThat(response.message()).isEqualTo("Device was booked");
		Order order = orderCaptor.getValue();
		assertThat(order.getDevice().getStatus()).isEqualTo(DeviceStatus.BOOKED);
		assertThat(order.getOrderTime()).isBeforeOrEqualTo(LocalDateTime.now(ZoneOffset.UTC));
	}

	@Test
	@DisplayName("when the device is booked then should throw an exception")
	void shouldThrowDeviceException() {
		//GIVEN
		when(deviceService.findById(BOOKED_DEVICE_ID)).thenReturn(getBooked());
		DeviceOrderRequestDTO requestDto = new DeviceOrderRequestDTO(USER_ID, BOOKED_DEVICE_ID);
		//WHEN THEN
		assertThatThrownBy(() -> service.orderDevice(requestDto))
				.isInstanceOf(DeviceException.class)
				.hasMessage("Device was booked");
	}

	private Device getAvailable() {
		return new Device(AVAILABLE_DEVICE_ID, "Name", DeviceStatus.AVAILABLE, 0);
	}

	private Device getBooked() {
		return new Device(BOOKED_DEVICE_ID, "Name", DeviceStatus.BOOKED, 0);
	}

}

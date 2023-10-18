package com.example.testdevice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testdevice.dto.DeviceOrderRequestDTO;
import com.example.testdevice.dto.DeviceOrderResponseDTO;
import com.example.testdevice.service.DeviceOrderService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("api/device-order")
@AllArgsConstructor
public class DeviceOrderController {

	private DeviceOrderService service;

	@PutMapping
	public DeviceOrderResponseDTO orderDevice(@RequestBody DeviceOrderRequestDTO dto) {
		return service.orderDevice(dto);
	}

	@DeleteMapping
	public DeviceOrderResponseDTO releaseDevice(@RequestBody DeviceOrderRequestDTO dto) {
		return service.releaseDevice(dto);
	}

}

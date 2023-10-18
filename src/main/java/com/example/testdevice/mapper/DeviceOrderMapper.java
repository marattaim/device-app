package com.example.testdevice.mapper;

import org.mapstruct.Mapper;

import com.example.testdevice.dto.DeviceOrderResponseDTO;

@Mapper(componentModel = "spring")
public interface DeviceOrderMapper {
	DeviceOrderResponseDTO toResponseDto(String message);
}

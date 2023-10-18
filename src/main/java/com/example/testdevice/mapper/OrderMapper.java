package com.example.testdevice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.testdevice.domain.Device;
import com.example.testdevice.domain.Order;
import com.example.testdevice.domain.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, ZoneOffset.class})
public interface OrderMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "orderTime", expression = "java(LocalDateTime.now(ZoneOffset.UTC))")
	Order toOrder(User user, Device device);

}

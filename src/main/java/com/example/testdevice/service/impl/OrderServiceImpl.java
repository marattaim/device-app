package com.example.testdevice.service.impl;

import org.springframework.stereotype.Service;

import com.example.testdevice.domain.Order;
import com.example.testdevice.repository.OrderRepository;
import com.example.testdevice.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderRepository repository;

	public void create(Order order) {
		log.debug("Try to save order {}", order);
		repository.save(order);
		log.debug("Order {} saved", order);
	}
}

package com.example.testdevice.service;

import com.example.testdevice.domain.Order;

public interface OrderService {

	/**
	 * Crate new order
	 * @param order to create
	 */
	void create(Order order);

}

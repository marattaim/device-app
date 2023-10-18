package com.example.testdevice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.testdevice.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}

package com.example.testdevice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.testdevice.domain.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
}

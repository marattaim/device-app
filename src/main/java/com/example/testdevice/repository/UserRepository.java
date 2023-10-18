package com.example.testdevice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.testdevice.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

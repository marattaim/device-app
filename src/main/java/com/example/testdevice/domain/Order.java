package com.example.testdevice.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "order_tbl")
public class Order {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "device_id", nullable = false)
	private Device device;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "order_time", nullable = false)
	private LocalDateTime orderTime;

}

package com.example.testdevice.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "device_tbl")
public class Device {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "device_name", length = 50)
	private String name;

	@Enumerated(value = STRING)
	private DeviceStatus status;

	@Version
	private Integer version;

}

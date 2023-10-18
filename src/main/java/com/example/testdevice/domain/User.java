package com.example.testdevice.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "user_tbl")
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "user_name", length = 30)
	private String name;

}

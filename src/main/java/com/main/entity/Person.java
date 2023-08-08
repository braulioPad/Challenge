package com.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "people")
@Data
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String handle;
	private String name;
}
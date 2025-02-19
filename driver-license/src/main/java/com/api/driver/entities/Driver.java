package com.api.driver.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Table
@Entity
@Data
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int age;
	@JsonManagedReference
	@OneToMany(mappedBy = "driver")
	private List<Car> cars;
	
	public Driver() {
		
	}
	
	public Driver(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
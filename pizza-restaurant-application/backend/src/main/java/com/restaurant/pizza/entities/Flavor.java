package com.restaurant.pizza.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Flavor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Float value;
	
	public Flavor() {
		
	}
	
	public Flavor(Long id, String name, String description, Float value) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.value = value;
	}

}

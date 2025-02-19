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
public class Edge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Float value;
	
	public Edge() {
		
	}
	
	public Edge(Long id, String name, Float value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

}

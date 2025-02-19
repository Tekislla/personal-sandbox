package com.restaurant.pizza.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "usuario")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long phone;
	private String email;
	private String password;
	private String street;
	private String district;
	private String complement;
	private Integer number;
	
	public User() {
		
	}

}

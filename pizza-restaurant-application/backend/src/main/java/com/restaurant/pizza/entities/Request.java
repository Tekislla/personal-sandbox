package com.restaurant.pizza.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
	private User user;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id")
	private Pizza pizza;
	private LocalDateTime dateTime;
	private Boolean processed;
	
	public Request() {
		
	}

	public Request(Long id, User user, Pizza pizza, LocalDateTime dateTime, Boolean processed) {
		this.id = id;
		this.user = user;
		this.pizza = pizza;
		this.dateTime = dateTime;
		this.processed = processed;
	}
	
	

}

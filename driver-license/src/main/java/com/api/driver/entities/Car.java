package com.api.driver.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Table
@Entity
@Data
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String model;
	private int year;
	private LocalDate purchaseDate;
	@JsonBackReference
	@ManyToOne
	private Driver driver;
	@OneToOne(mappedBy = "car")
	private License license;
	
	public Car() {
		
	}
	
	public Car(Long id, String brand, String model, int year, LocalDate purchaseDate) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.purchaseDate = purchaseDate;
	}
}
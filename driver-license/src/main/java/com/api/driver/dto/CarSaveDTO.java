package com.api.driver.dto;

import java.time.LocalDate;

import com.api.driver.entities.Driver;

import lombok.Data;

@Data
public class CarSaveDTO {
	private String brand;
	private String model;
	private int year;
	private LocalDate purchaseDate;
	private Driver driver;
	
	public CarSaveDTO() {
		
	}
	
	public CarSaveDTO(String brand, String model, int year, Driver driver, LocalDate purchaseDate) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.driver = driver;
		this.purchaseDate = purchaseDate;
	}
}

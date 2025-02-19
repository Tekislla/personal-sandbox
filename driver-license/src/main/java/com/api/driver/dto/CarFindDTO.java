package com.api.driver.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CarFindDTO {
	private Long id;
	private String brand;
	private String model;
	private int year;
	private LocalDate purchaseDate;
	
	public CarFindDTO() {
		
	}
	
	public CarFindDTO(Long id, String brand, String model, int year, LocalDate purchaseDate) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.purchaseDate = purchaseDate;
	}

}

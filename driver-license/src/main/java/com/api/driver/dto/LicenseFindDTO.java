package com.api.driver.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LicenseFindDTO {
	private Long id;
	private LocalDate issueDate;
	private LocalDate expirationDate;
	private DriverFindDTO driver;
	private CarFindDTO car;
	
	public LicenseFindDTO() {
		
	}
	
	public LicenseFindDTO(Long id, LocalDate issueDate, LocalDate expirationDate) {
		this.id = id;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
	}
	
	public LicenseFindDTO(Long id, LocalDate issueDate, LocalDate expirationDate, Long driverId, String driverName, int driverAge, Long carId, String carBrand, String carModel, int carYear, LocalDate carPurchaseDate) {
		this.id = id;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
		this.driver = new DriverFindDTO(driverId, driverName, driverAge);
		this.car = new CarFindDTO(carId, carBrand, carModel, carYear, carPurchaseDate);
	}

}
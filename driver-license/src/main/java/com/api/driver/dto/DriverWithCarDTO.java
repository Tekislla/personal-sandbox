package com.api.driver.dto;

import java.util.List;

import lombok.Data;

@Data
public class DriverWithCarDTO {
	private DriverFindDTO driver;
	private List<CarFindDTO> cars;
	
	public DriverWithCarDTO() {
		
	}
	
	public DriverWithCarDTO(DriverFindDTO driver, List<CarFindDTO> cars) {
		this.driver = driver;
		this.cars = cars;
	}

}

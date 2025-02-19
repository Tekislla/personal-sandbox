package com.api.driver.dto;

import lombok.Data;

@Data
public class CarWithDriverAndLicenseDTO {
	private CarFindDTO car;
	private DriverFindDTO driver;
	private LicenseForCarFindDTO license;
	
	public CarWithDriverAndLicenseDTO() {
		
	}
	
	public CarWithDriverAndLicenseDTO(CarFindDTO car, DriverFindDTO driver, LicenseForCarFindDTO license) {
		this.car = car;
		this.driver = driver;
		this.license = license;
	}

}

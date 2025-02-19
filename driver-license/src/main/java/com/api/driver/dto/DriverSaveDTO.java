package com.api.driver.dto;

import lombok.Data;

@Data
public class DriverSaveDTO {
	private String name;
	private int age;
	
	public DriverSaveDTO() {
		
	}
	
	public DriverSaveDTO(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

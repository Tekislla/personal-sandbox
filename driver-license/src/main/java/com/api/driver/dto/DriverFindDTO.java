package com.api.driver.dto;

import lombok.Data;

@Data
public class DriverFindDTO {
	private Long id;
	private String name;
	private int age;
	
	public DriverFindDTO() {
		
	}
	
	public DriverFindDTO(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

}

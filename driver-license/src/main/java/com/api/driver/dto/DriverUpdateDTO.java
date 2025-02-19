package com.api.driver.dto;

import javax.validation.constraints.NotNull;

public class DriverUpdateDTO extends DriverSaveDTO {
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
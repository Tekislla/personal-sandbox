package com.joao.movie.dto;

import javax.validation.constraints.NotNull;

public class MovieUpdateDTO extends MovieSaveDTO {
	@NotNull
	private Long ID;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
}
package com.joao.movie.dto;

import javax.validation.constraints.NotNull;

public class ActorUpdateDTO extends ActorSaveDTO {
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

package com.joao.movie.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ActorSaveDTO {
	@NotNull
	private String name;
	
}

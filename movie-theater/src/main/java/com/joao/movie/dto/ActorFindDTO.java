package com.joao.movie.dto;

import lombok.Data;

@Data
public class ActorFindDTO {
	private Long id;
	private String name;
	
	public ActorFindDTO() {
		
	}
	
	public ActorFindDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}

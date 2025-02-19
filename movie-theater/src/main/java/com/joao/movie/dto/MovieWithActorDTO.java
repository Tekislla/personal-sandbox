package com.joao.movie.dto;

import java.util.List;

import lombok.Data;

@Data
public class MovieWithActorDTO {
	private MovieFindDTO movie;
	private List<ActorFindDTO> actors;
	
	public MovieWithActorDTO() {
		
	}
	
	public MovieWithActorDTO(MovieFindDTO movie, List<ActorFindDTO> actors) {
		this.movie = movie;
		this.actors = actors;
	}
}

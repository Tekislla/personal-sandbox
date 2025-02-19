package com.joao.movie.dto;

import java.util.List;

import lombok.Data;

@Data
public class ActorWithMovieDTO {
	private ActorFindDTO actor;
	private List<MovieFindDTO> movies;
	
	public ActorWithMovieDTO() {
		
	}
	
	public ActorWithMovieDTO(ActorFindDTO actor, List<MovieFindDTO> movies) {
		this.actor = actor;
		this.movies = movies;
	}

}

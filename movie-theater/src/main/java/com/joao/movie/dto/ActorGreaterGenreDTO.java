package com.joao.movie.dto;

import com.joao.movie.entities.Genre;

import lombok.Data;

@Data
public class ActorGreaterGenreDTO {
	private Long count_genre;
	private String name;
	private Genre genre;
	
	public ActorGreaterGenreDTO() {
		
	}
	
	public ActorGreaterGenreDTO(Long count_genre, String name, Genre genre) {
		this.count_genre = count_genre;
		this.name = name;
		this.genre = genre;
	}
}
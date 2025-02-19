package com.joao.movie.dto;

import java.time.LocalDate;

import com.joao.movie.entities.Genre;

import lombok.Data;

@Data
public class MovieFindDTO {
	private Long id;
	private String movieName;
	private Float rate;
	private LocalDate launchDate;
	private Genre genre;

	public MovieFindDTO() {
		
	}
	
	public MovieFindDTO(Long id, String movieName, Float rate, LocalDate launchDate, Genre genre) {
		this.id = id;
		this.movieName = movieName;
		this.rate = rate;
		this.launchDate = launchDate;
		this.genre = genre;
	}
	
}

package com.joao.movie.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.joao.movie.entities.Genre;

import lombok.Data;

@Data
public class MovieSaveDTO {
	@NotBlank
	private String movieName;
	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private Float rate;
	@NotNull
	private LocalDate launchDate;
	@NotNull
	private Genre genre;
	
	private List<ActorSaveDTO> actors;
}

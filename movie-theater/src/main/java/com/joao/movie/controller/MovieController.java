package com.joao.movie.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joao.movie.dto.MovieFindDTO;
import com.joao.movie.dto.MovieSaveDTO;
import com.joao.movie.dto.MovieUpdateDTO;
import com.joao.movie.dto.MovieWithActorDTO;
import com.joao.movie.entities.Actor;
import com.joao.movie.entities.Genre;
import com.joao.movie.entities.Movie;
import com.joao.movie.service.MovieService;

@RestController
@RequestMapping(value = "movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping(value = "/add-movie")
	public long addMovie(@RequestBody @Valid MovieSaveDTO movieDto) {
		return movieService.addMovie(movieDto);
	}
	
//	@GetMapping(value = "/find-all-movies")
//	public List<Movie> findAll() {
//		return movieService.findAll();
//	}
	
	@GetMapping(value = "/find-all")
	public List<MovieWithActorDTO> findMovies() {
		return movieService.findMovies();
	}
	
	@GetMapping(value = "/find-movie/{id}")
	public Movie findMovie(@PathVariable Long id) {
		return movieService.findMovie(id);
	}
	
	@GetMapping(value = "/find-movie-actor-id/{id}")
	public List<MovieFindDTO> findMovieByActorId(@PathVariable Long id) {
		return movieService.findMovieByActorId(id);
	}
	
	@GetMapping(value = "/find-movie-genre")
	public List<Movie> findMovieByGenre(@RequestParam String genre) {
		return movieService.findByGenre(Genre.valueOf(genre));
	}
	
	@GetMapping(value = "/find-actor-name")
	public Actor findActorByName(@RequestParam String name) {
		return movieService.findActorByName(name);
	}
	
	@PostMapping(value = "/update-movie")
	public void updateMovie(@RequestBody MovieUpdateDTO movieDto) {
		movieService.updateMovie(movieDto);
	}
	
	@PostMapping(value = "/delete-movie/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
	}
	
	@GetMapping(value = "/find-launchdate-greater/{launchDate}")
	public List<Movie> getMoviesLaunchDateGreaterThanEqual(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate launchDate) {
		return movieService.findByLaunchDateGreaterThanEqual(launchDate);
	}
	
	@GetMapping(value = "/find-launchdate-less/{launchDate}")
	public List<Movie> getMoviesLaunchDateLessThanEqual(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate launchDate) {
		return movieService.findByLaunchDateLessThanEqual(launchDate);
	}
}

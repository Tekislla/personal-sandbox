package com.joao.movie.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.movie.dto.ActorFindDTO;
import com.joao.movie.dto.ActorSaveDTO;
import com.joao.movie.dto.MovieFindDTO;
import com.joao.movie.dto.MovieSaveDTO;
import com.joao.movie.dto.MovieUpdateDTO;
import com.joao.movie.dto.MovieWithActorDTO;
import com.joao.movie.entities.Actor;
import com.joao.movie.entities.Genre;
import com.joao.movie.entities.Movie;
import com.joao.movie.exceptions.MovieNotFoundException;
import com.joao.movie.repository.ActorRepository;
import com.joao.movie.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ActorRepository actorRepository;
	
	public Long addMovie(MovieSaveDTO movieDto) {
		Movie movie = new Movie();
		movie.setActors(new ArrayList<Actor>());
		for (ActorSaveDTO actorDto : movieDto.getActors()) {
			Actor actor = actorRepository.findByName(actorDto.getName());
			if (actor == null) {
				actor = new Actor();
				actor.setMovies(new ArrayList<Movie>());
				actor.getMovies().add(movie);
				actor.setName(actorDto.getName());
				movie.getActors().add(actor);
			} else {
				actor.getMovies().add(movie);
				movie.getActors().add(actor);
			}	
		}
		movie.setMovieName(movieDto.getMovieName());
		movie.setGenre(movieDto.getGenre());
		movie.setLaunchDate(movieDto.getLaunchDate());
		movie.setRate(movieDto.getRate());
		movieRepository.save(movie);
		
		return movie.getId();
	}
	
	public List<Movie> findAll() {
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		return movies;
	}
	
	public List<MovieWithActorDTO> findMovies() {
		List<MovieWithActorDTO> moviesWithActors = new ArrayList<MovieWithActorDTO>();
		List<MovieFindDTO> movies = movieRepository.findMovies();
		for (MovieFindDTO movieDto : movies) {;
				List<ActorFindDTO> actors = actorRepository.findActorByMovieId(movieDto.getId());
				moviesWithActors.add(new MovieWithActorDTO(movieDto, actors));
			}
		return moviesWithActors;
	}
	
	public Actor findActorByName(String name) {
		Actor actor = actorRepository.findByName(name);
		return actor;
	}
	
	
	public Movie findMovie(Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		return movie.get();
	}
	
	public List<Movie> findByGenre(Genre genre) {
		List<Movie> movies = movieRepository.findByGenre(genre);
		return movies;
	}
	
	public List<MovieFindDTO> findMovieByActorId(Long id) {
		return movieRepository.findMovieByActorId(id);
	}
	
	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}
	
	public void updateMovie(MovieUpdateDTO movieDto) {
		Movie movie = new Movie();
		movie.setId(movieDto.getID());
		movie.setMovieName(movieDto.getMovieName());
		movie.setGenre(movieDto.getGenre());
		movie.setLaunchDate(movieDto.getLaunchDate());
		movie.setRate(movieDto.getRate());
		if (movieRepository.existsById(movie.getId())) {
			movieRepository.save(movie);
		} else {
			throw new MovieNotFoundException();
		}
	}
	
	public List<Movie> findByLaunchDateGreaterThanEqual(LocalDate launchDate) {
		List<Movie> movies = movieRepository.findByLaunchDateGreaterThanEqual(launchDate);
		return movies;
	}
	
	public List<Movie> findByLaunchDateLessThanEqual(LocalDate launchDate) {
		List<Movie> movies = movieRepository.findByLaunchDateLessThanEqual(launchDate);
		return movies;
	}
}
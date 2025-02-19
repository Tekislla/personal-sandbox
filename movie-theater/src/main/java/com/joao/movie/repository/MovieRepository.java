package com.joao.movie.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joao.movie.dto.MovieFindDTO;
import com.joao.movie.entities.Genre;
import com.joao.movie.entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	
	@Query(value = "SELECT new com.joao.movie.dto.MovieFindDTO(m.id, m.movieName, m.rate, m.launchDate, m.genre) FROM Movie m")
	public List<MovieFindDTO> findMovies();
	
	@Query(value = "SELECT new com.joao.movie.dto.MovieFindDTO(m.id, m.movieName, m.rate, m.launchDate, m.genre) FROM Movie m INNER JOIN m.actors a WHERE a.id = ?1")
	public List<MovieFindDTO> findMovieByActorId(Long id);
	
	public List<Movie> findByGenre(Genre genre);
	
	public List<Movie> findByLaunchDateGreaterThanEqual(LocalDate launchDate);
	
	public List<Movie> findByLaunchDateLessThanEqual(LocalDate launchDate);
	
	
}
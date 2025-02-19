package com.joao.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.joao.movie.dto.ActorFindDTO;
import com.joao.movie.dto.ActorGreaterGenreDTO;
import com.joao.movie.dto.ActorSaveDTO;
import com.joao.movie.dto.ActorUpdateDTO;
import com.joao.movie.dto.ActorWithMovieDTO;
import com.joao.movie.dto.MovieFindDTO;
import com.joao.movie.entities.Actor;
import com.joao.movie.entities.Movie;
import com.joao.movie.exceptions.MovieNotFoundException;
import com.joao.movie.repository.ActorRepository;
import com.joao.movie.repository.MovieRepository;

@Service
public class ActorService {
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private MovieRepository movieRepository;
	
	public Long addActor(ActorSaveDTO actorDto) {
		Actor actor = new Actor();
		actor.setMovies(new ArrayList<Movie>());
		actor.setName(actorDto.getName());
		actorRepository.save(actor);
		
		return actor.getId();
	}
	
	public List<Actor> findAll() {
		List<Actor> actors = (List<Actor>) actorRepository.findAll();
		return actors;
	}
	
	public List<ActorWithMovieDTO> findActors() {
		List<ActorWithMovieDTO> actorWithMovies = new ArrayList<ActorWithMovieDTO>();
		List<ActorFindDTO> actors = actorRepository.findActors();
		for (ActorFindDTO actorDto : actors) {
				List<MovieFindDTO> movies = movieRepository.findMovieByActorId(actorDto.getId());
				actorWithMovies.add(new ActorWithMovieDTO(actorDto, movies));
			}
		return actorWithMovies;
	}
 	
	
	public Actor findActor(Long id) {
		Optional<Actor> actor = actorRepository.findById(id);
		return actor.get();
	}
	
	public List<ActorFindDTO> findActorByMovieId(Long id) {
		return actorRepository.findActorByMovieId(id);
	}
	
	public void deleteActor(Long id) {
		actorRepository.deleteById(id);
	}
	
	public void updateActor(ActorUpdateDTO actorDto) {
		Actor actor = new Actor();
		actor.setId(actorDto.getId());
		actor.setName(actorDto.getName());
		if (actorRepository.existsById(actor.getId())) {
			actorRepository.save(actor);
		} else {
			throw new MovieNotFoundException();
		}
	}
	
	public List<ActorGreaterGenreDTO> actorGreaterGenre(Long id) {
		List<ActorGreaterGenreDTO> actors = actorRepository.actorGreaterGenre(id, PageRequest.of(0, 1));
		return actors;
	}
}

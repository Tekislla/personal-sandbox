package com.joao.movie.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.movie.dto.ActorFindDTO;
import com.joao.movie.dto.ActorGreaterGenreDTO;
import com.joao.movie.dto.ActorSaveDTO;
import com.joao.movie.dto.ActorUpdateDTO;
import com.joao.movie.dto.ActorWithMovieDTO;
import com.joao.movie.entities.Actor;
import com.joao.movie.service.ActorService;

@RestController
@RequestMapping(value = "actor")
public class ActorController {

	@Autowired
	private ActorService actorService;

	@PostMapping(value = "/add-actor")
	public long addActor(@RequestBody @Valid ActorSaveDTO actorDto) {
		return actorService.addActor(actorDto);
	}

	@GetMapping(value = "/find-all-actors")
	public List<Actor> findAll() {
		return actorService.findAll();
	}

	@GetMapping(value = "/find-all-actors-dto")
	public List<ActorWithMovieDTO> findActors() {
		return actorService.findActors();
	}

	@GetMapping(value = "/find-actor/{id}")
	public Actor findActor(@PathVariable Long id) {
		return actorService.findActor(id);
	}

	@GetMapping(value = "/find-actor-dto/{id}")
	public List<ActorFindDTO> findActorByMovieId(@PathVariable Long id) {
		return actorService.findActorByMovieId(id);
	}

	@PostMapping(value = "/update-actor")
	public void updateActor(@RequestBody ActorUpdateDTO actorDto) {
		actorService.updateActor(actorDto);
	}

	@PostMapping(value = "/delete-actor/{id}")
	public void deleteActor(@PathVariable Long id) {
		actorService.deleteActor(id);
	}

	@GetMapping(value = "/greater-genre/{id}")
	public List<ActorGreaterGenreDTO> actorGreaterGenre(@PathVariable Long id) {
		return actorService.actorGreaterGenre(id);
	}
}
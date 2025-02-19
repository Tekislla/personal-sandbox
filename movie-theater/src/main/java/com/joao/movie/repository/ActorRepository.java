package com.joao.movie.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joao.movie.dto.ActorFindDTO;
import com.joao.movie.dto.ActorGreaterGenreDTO;
import com.joao.movie.entities.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long> {

	@Query(value = "SELECT new com.joao.movie.dto.ActorFindDTO(a.id, a.name) FROM Actor a")
	public List<ActorFindDTO> findActors();
	
	@Query(value = "SELECT new com.joao.movie.dto.ActorFindDTO(a.id, a.name) FROM Actor a INNER JOIN a.movies m WHERE m.id = ?1")
	public List<ActorFindDTO> findActorByMovieId(Long id);
	
	public Actor findByName(String name);
	
	@Query(value = "SELECT new com.joao.movie.dto.ActorGreaterGenreDTO(count(m.genre), a.name, m.genre) FROM Actor a JOIN a.movies m WHERE a.id = ?1 GROUP BY a.name, m.genre ORDER BY 1 desc")
	public List<ActorGreaterGenreDTO> actorGreaterGenre(Long id, Pageable pageable);
}
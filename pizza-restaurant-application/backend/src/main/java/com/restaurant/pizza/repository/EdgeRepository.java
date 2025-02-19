package com.restaurant.pizza.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.restaurant.pizza.entities.Edge;

public interface EdgeRepository extends CrudRepository<Edge, Long> {
	@Query(value = "SELECT new com.restaurant.pizza.entities.Edge(e.id, e.name, e.value) FROM Edge e WHERE e.name = ?1")
	public Edge findByName(String name);
}

package com.restaurant.pizza.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.restaurant.pizza.entities.Flavor;

public interface FlavorRepository extends CrudRepository<Flavor, Long> {
	@Query(value = "SELECT new com.restaurant.pizza.entities.Flavor(f.id, f.name, f.description, f.value) FROM Flavor f WHERE f.name = ?1")
	public Flavor findByName(String name);

}

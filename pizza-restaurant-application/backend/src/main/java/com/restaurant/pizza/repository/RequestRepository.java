package com.restaurant.pizza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.restaurant.pizza.entities.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM request WHERE processed = false ORDER BY date_time ASC LIMIT 1;")
	public Optional <Request> findFirstRequest();

}

package com.restaurant.pizza.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.pizza.entities.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}

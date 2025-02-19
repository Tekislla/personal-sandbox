package com.restaurant.pizza.repository;

import org.springframework.data.repository.CrudRepository;

import com.restaurant.pizza.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

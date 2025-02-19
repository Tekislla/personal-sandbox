package com.restaurant.pizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.pizza.entities.User;
import com.restaurant.pizza.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/create")
	public Long createUser(@RequestBody User userDto) {
		return userService.createUser(userDto);
	}
	
	@GetMapping(value = "/find-all")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PostMapping(value = "/update/{id}")
	public void updateUser(@PathVariable Long id, @RequestBody User userDto) {
		userService.updateUser(id, userDto);
	}
	
	@PostMapping(value = "/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
}
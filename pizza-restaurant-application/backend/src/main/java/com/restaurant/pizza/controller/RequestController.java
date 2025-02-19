package com.restaurant.pizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.pizza.entities.Request;
import com.restaurant.pizza.scheduler.RequestScheduler;
import com.restaurant.pizza.service.RequestService;

@RestController
@CrossOrigin
@RequestMapping(value = "/request")
public class RequestController {
	@Autowired
	RequestService requestService;
	@Autowired
	RequestScheduler requestScheduler;
	
	@PostMapping(value = "/create/{pizzaId}")
	public void createRequest(@PathVariable Long pizzaId) {
		requestService.makeRequest(pizzaId);
	}
	
	@GetMapping(value = "/find-all")
	public List<Request> findAll() {
		return requestService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public Request findById(@PathVariable Long id) {
		return requestService.findById(id);
	}
	
	@GetMapping(value = "/test")
	public Request test() {
		return requestService.testFind();
	}
	
//	@PostMapping(value = "/update/{id}")
//	public void updateRequest(@PathVariable Long id, @RequestBody Request requestDto) {
//		requestService.updateRequest(id, requestDto);
//	}
	
	@PostMapping(value = "/delete/{id}")
	public void deleteRequest(@PathVariable Long id) {
		requestService.deleteRequest(id);
	}
	
}
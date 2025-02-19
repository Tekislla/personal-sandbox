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

import com.restaurant.pizza.dto.PizzaDto;
import com.restaurant.pizza.entities.Pizza;
import com.restaurant.pizza.service.PizzaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/pizza")
public class PizzaController {
	@Autowired
	PizzaService pizzaService;
	
	@PostMapping(value = "/create")
	public Long createPizza(@RequestBody PizzaDto pizzaDto) {
		return pizzaService.buyPizza(pizzaDto);
	}
	
	@GetMapping(value = "/find-all")
	public List<Pizza> findAll() {
		return pizzaService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public Pizza findById(@PathVariable Long id) {
		return pizzaService.findById(id);
	}
	
//	@PostMapping(value = "/update/{id}")
//	public void updatePizza(@PathVariable Long id, @RequestBody Pizza pizzaDto) {
//		pizzaService.updatePizza(id, pizzaDto);
//	}
//	
	@PostMapping(value = "/delete/{id}")
	public void deletePizza(@PathVariable Long id) {
		pizzaService.deletePizza(id);
	}
	
}
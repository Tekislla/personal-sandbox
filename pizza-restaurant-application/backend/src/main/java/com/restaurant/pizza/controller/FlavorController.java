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

import com.restaurant.pizza.entities.Flavor;
import com.restaurant.pizza.service.FlavorService;

@RestController
@CrossOrigin
@RequestMapping(value = "/flavor")
public class FlavorController {
	@Autowired
	FlavorService flavorService;
	
	@PostMapping(value = "/create")
	public void createFlavor(@RequestBody Flavor flavorDto) {
		flavorService.createFlavor(flavorDto);
	}
	
	@GetMapping(value = "/find-all")
	public List<Flavor> findAll() {
		return flavorService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public Flavor findById(@PathVariable Long id) {
		return flavorService.findById(id);
	}
	
	@PostMapping(value = "/update/{id}")
	public void updateFlavor(@PathVariable Long id, @RequestBody Flavor flavorDto) {
		flavorService.updateFlavor(id, flavorDto);
	}
	
	@PostMapping(value = "/delete/{id}")
	public void deleteFlavor(@PathVariable Long id) {
		flavorService.deleteFlavor(id);
	}
	
}
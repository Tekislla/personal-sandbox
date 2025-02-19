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

import com.restaurant.pizza.entities.Edge;
import com.restaurant.pizza.service.EdgeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/edge")
public class EdgeController {
	@Autowired
	EdgeService edgeService;
	
	@PostMapping(value = "/create")
	public void createEdge(@RequestBody Edge edgeDto) {
		edgeService.addEdge(edgeDto);
	}
	
	@GetMapping(value = "/find-all")
	public List<Edge> findAll() {
		return edgeService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public Edge findById(@PathVariable Long id) {
		return edgeService.findById(id);
	}
	
	@PostMapping(value = "/update/{id}")
	public void updateEdge(@PathVariable Long id, @RequestBody Edge edgeDto) {
		edgeService.updateEdge(id, edgeDto);
	}
	
	@PostMapping(value = "/delete/{id}")
	public void deleteEdge(@PathVariable Long id) {
		edgeService.deleteEdge(id);
	}
	
}
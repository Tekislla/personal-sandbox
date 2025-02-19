package com.restaurant.pizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pizza.entities.Edge;
import com.restaurant.pizza.repository.EdgeRepository;

@Service
public class EdgeService {
	@Autowired
	private EdgeRepository edgeRepository;
	
	public void addEdge(Edge edgeDto) {
		Edge edge = new Edge();
		edge.setName(edgeDto.getName());
		edge.setValue(edgeDto.getValue());
		
		edgeRepository.save(edge);
	}
	
	public List<Edge> findAll() {
		return (List<Edge>) edgeRepository.findAll();
	}
	
	public Edge findById(Long id) {
		return edgeRepository.findById(id).get();
	}
	
	public void updateEdge(Long id, Edge edgeDto) {
		Edge edge = edgeRepository.findById(id).get();
		edge.setName(edgeDto.getName());
		edge.setValue(edgeDto.getValue());
		
		edgeRepository.save(edge);
	}
	
	public void deleteEdge(Long id) {
		edgeRepository.deleteById(id);
	}
	
}

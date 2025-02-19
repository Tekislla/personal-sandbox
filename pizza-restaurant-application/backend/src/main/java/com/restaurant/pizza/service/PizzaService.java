package com.restaurant.pizza.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pizza.dto.FlavorDto;
import com.restaurant.pizza.dto.PizzaDto;
import com.restaurant.pizza.entities.Edge;
import com.restaurant.pizza.entities.Flavor;
import com.restaurant.pizza.entities.Pizza;
import com.restaurant.pizza.repository.EdgeRepository;
import com.restaurant.pizza.repository.FlavorRepository;
import com.restaurant.pizza.repository.PizzaRepository;

@Service
public class PizzaService {
	@Autowired
	private PizzaRepository pizzaRepository;
	@Autowired
	private FlavorRepository flavorRepository;
	@Autowired
	private EdgeRepository edgeRepository;
	
	public Long buyPizza(PizzaDto pizzaDto) {
		Pizza pizza = new Pizza();
		Edge edge = edgeRepository.findById(pizzaDto.getEdge().getId()).get();
		
		List<Flavor> listFlavors = new ArrayList<Flavor>();
		List<FlavorDto> listFlavorsDto = pizzaDto.getFlavor();
		
		pizza.setValue(0f);
		
		for(FlavorDto flavorDto : listFlavorsDto) {
			Flavor flavor = flavorRepository.findById(flavorDto.getId()).get();
			listFlavors.add(flavor);
			
			pizza.setValue(pizza.getValue() + flavor.getValue());
		}
		
		pizza.setValue(pizza.getValue() + edge.getValue());
		pizza.setSize(pizzaDto.getSize());
		pizza.setEdge(edge);
		pizza.setFlavor(listFlavors);
		
		pizzaRepository.save(pizza);
		return pizza.getId();
	}
	
	public List<Pizza> findAll() {
		return (List<Pizza>) pizzaRepository.findAll();
	}
	
	public Pizza findById(Long id) {
		return pizzaRepository.findById(id).get();
	}
	
	public void deletePizza(Long id) {
		pizzaRepository.deleteById(id);
	}
		
}
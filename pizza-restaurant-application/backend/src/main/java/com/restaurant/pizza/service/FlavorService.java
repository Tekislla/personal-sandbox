package com.restaurant.pizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pizza.entities.Flavor;
import com.restaurant.pizza.repository.FlavorRepository;

@Service
public class FlavorService {
	@Autowired
	private FlavorRepository flavorRepository;
	
	public void createFlavor(Flavor flavorDto) {
		Flavor flavor = new Flavor();
		flavor.setName(flavorDto.getName());
		flavor.setDescription(flavorDto.getDescription());
		flavor.setValue(flavorDto.getValue());
		
		flavorRepository.save(flavor);
	}
	
	public List<Flavor> findAll() {
		return (List<Flavor>) flavorRepository.findAll();
	}
	
	public Flavor findById(Long id) {
		return flavorRepository.findById(id).get();
	}
	
	public void updateFlavor(Long id, Flavor flavorDto) {
		Flavor flavor = flavorRepository.findById(id).get();
		flavor.setName(flavorDto.getName());
		flavor.setDescription(flavorDto.getDescription());
		flavor.setValue(flavorDto.getValue());
		
		flavorRepository.save(flavor);
	}
	
	public void deleteFlavor(Long id) {
		flavorRepository.deleteById(id);
	}
	
}
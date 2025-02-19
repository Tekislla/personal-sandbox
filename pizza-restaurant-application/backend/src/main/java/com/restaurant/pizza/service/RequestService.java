package com.restaurant.pizza.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.restaurant.pizza.entities.Pizza;
import com.restaurant.pizza.entities.Request;
import com.restaurant.pizza.entities.User;
import com.restaurant.pizza.repository.PizzaRepository;
import com.restaurant.pizza.repository.RequestRepository;
import com.restaurant.pizza.repository.UserRepository;

@Service
public class RequestService {
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PizzaRepository pizzaRepository;
	
	public void makeRequest(Long pizzaId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) auth.getPrincipal();
		Long userId = jwt.getClaim("id");
		User user = userRepository.findById(userId).get();
		Pizza pizza = pizzaRepository.findById(pizzaId).get();
		Request request = new Request();
		
		request.setUser(user);
		request.setPizza(pizza);
		request.setDateTime(LocalDateTime.now());
		request.setProcessed(false);
		
		requestRepository.save(request);
	}
	
	

	public Request testFind() {
		return requestRepository.findFirstRequest().get();
	}
	
	public List<Request> findAll() {
		return (List<Request>) requestRepository.findAll();
	}
	
	public Request findById(Long id) {
		return requestRepository.findById(id).get();
	}
	
	public void deleteRequest(Long id) {
		requestRepository.findById(id).get();
	}
	
}
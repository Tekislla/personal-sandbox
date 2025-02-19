package com.restaurant.pizza.scheduler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.restaurant.pizza.entities.Request;
import com.restaurant.pizza.repository.RequestRepository;

@Component
public class RequestScheduler {
	@Autowired
	private RequestRepository requestRepository;
	
	@Scheduled(fixedDelay = 60000)
	public void updateRequestTime() {
		Optional<Request> request = requestRepository.findFirstRequest();
		if(request.isPresent()) {
			request.get().setProcessed(true);
			requestRepository.save(request.get());
			System.out.println("passou");
		} else {
			System.out.println("sem pedidos");
		}
		
		
	}

}

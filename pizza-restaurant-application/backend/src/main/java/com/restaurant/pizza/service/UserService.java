package com.restaurant.pizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pizza.entities.User;
import com.restaurant.pizza.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public Long createUser(User userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setPassword(userDto.getPassword());
		user.setStreet(userDto.getStreet());
		user.setDistrict(userDto.getDistrict());
		user.setNumber(userDto.getNumber());
		user.setComplement(userDto.getComplement());
		
		userRepository.save(user);
		return user.getId();
	}
	
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public void updateUser(Long id, User userDto) {
		User user = userRepository.findById(id).get();
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setPassword(userDto.getPassword());
		user.setStreet(userDto.getStreet());
		user.setDistrict(userDto.getDistrict());
		user.setNumber(userDto.getNumber());
		user.setComplement(userDto.getComplement());
		
		userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
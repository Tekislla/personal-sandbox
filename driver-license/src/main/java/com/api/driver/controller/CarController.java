package com.api.driver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.driver.dto.CarSaveDTO;
import com.api.driver.dto.CarUpdateDTO;
import com.api.driver.dto.CarWithDriverAndLicenseDTO;
import com.api.driver.service.CarService;

@RestController
@RequestMapping(value = "car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@PostMapping(value = "/buy-car/{id}")
	private Long buyCar(@PathVariable Long id, @RequestBody CarSaveDTO car) {
		return carService.buyCar(id, car);
	}
	
	@PostMapping(value = "/transfer-car/{ownerId}/{newOwnerId}/{carId}")
	private void transferCar(@PathVariable Long ownerId, @PathVariable Long newOwnerId, @PathVariable Long carId) {
		carService.transferCar(ownerId, newOwnerId, carId);
	}
	
	@GetMapping(value = "/find-all")
	private List<CarWithDriverAndLicenseDTO> findAll() {
		return carService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	private CarWithDriverAndLicenseDTO findById(@PathVariable Long id) {
		return carService.findById(id);
	}
	
	@PostMapping(value = "/update-car")
	private void updateCar(@RequestBody CarUpdateDTO car) {
		carService.updateCar(car);
	}
	
	@PostMapping(value = "/delete-car/{id}")
	private void deleteCar(@PathVariable Long id) {
		carService.deleteCar(id);
	}
	
}
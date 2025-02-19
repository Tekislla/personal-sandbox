package com.api.driver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.driver.dto.DriverSaveDTO;
import com.api.driver.dto.DriverUpdateDTO;
import com.api.driver.dto.DriverWithCarDTO;
import com.api.driver.service.DriverService;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {
	@Autowired
	private DriverService driverService;
	
	@PostMapping(value = "/create-driver")
	public Long createDriver(@RequestBody DriverSaveDTO driver) {
		return driverService.createDriver(driver);
	}

	@GetMapping(value = "/find-all")
	public List<DriverWithCarDTO> findAll() {
		return driverService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public DriverWithCarDTO findById(@PathVariable Long id) {
		return driverService.findById(id);
	}
	
	@PostMapping(value = "/update-driver")
	public void updateDriver(@RequestBody DriverUpdateDTO driver) {
		driverService.updateDriver(driver);
	}
	
	@PostMapping(value = "/delete-driver/{id}")
	public void deleteDriver(@PathVariable Long id) {
		driverService.deleteDriver(id);
	}
	
}

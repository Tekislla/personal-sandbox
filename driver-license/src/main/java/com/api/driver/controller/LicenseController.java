package com.api.driver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.driver.dto.LicenseFindDTO;
import com.api.driver.service.LicenseService;

@RestController
@RequestMapping(value = "/license")
public class LicenseController {
	@Autowired
	private LicenseService licenseService;
	
	@PostMapping(value = "/add-license/{driverId}/{carId}")
	public Long licenseCar(@PathVariable Long driverId, @PathVariable Long carId) {
		return licenseService.licenseCar(driverId, carId);
	}
	
	@GetMapping(value = "/find-all")
	public List<LicenseFindDTO> findAll() {
		return licenseService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public LicenseFindDTO findById(@PathVariable Long id) {
		return licenseService.findById(id);
	}
	
	@PostMapping(value = "/delete-license/{id}")
	public void deleteLicense(@PathVariable Long id) {
		licenseService.deleteLicense(id);
	}
}
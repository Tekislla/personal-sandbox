package com.api.driver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.driver.dto.LicenseFindDTO;
import com.api.driver.entities.Car;
import com.api.driver.entities.Driver;
import com.api.driver.entities.License;
import com.api.driver.repository.CarRepository;
import com.api.driver.repository.DriverRepository;
import com.api.driver.repository.LicenseRepository;

@Service
public class LicenseService {
	@Autowired
	private LicenseRepository licenseRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private DriverRepository driverRepository;
	
	public Long licenseCar(Long driverId, Long carId) {
		License license = new License();
		Car car = carRepository.findById(carId).get();
		Driver driver = driverRepository.findById(driverId).get();
		if (carRepository.existsById(car.getId()) && driverRepository.existsById(driver.getId())) {
			license.setIssueDate(car.getPurchaseDate());
			license.setExpirationDate(license.getIssueDate().plusYears(1));
			license.setDriver(driver);
			license.setCar(car);
			//car.setLicense(license);
		} else {
			throw new NullPointerException();
		}
		licenseRepository.save(license);
		return license.getId();
	}
	
	public List<LicenseFindDTO> findAll() {
		List<LicenseFindDTO> licenses = (List<LicenseFindDTO>) licenseRepository.findLicenses();
		return licenses;
	}
	
	public LicenseFindDTO findById(Long id) {
		Optional<LicenseFindDTO> license = licenseRepository.findLicenseById(id);
		return license.get();
	}
	
	public void deleteLicense(Long id) {
		licenseRepository.deleteById(id);
	}
	
}
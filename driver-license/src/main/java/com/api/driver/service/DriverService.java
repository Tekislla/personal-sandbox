package com.api.driver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.driver.dto.CarFindDTO;
import com.api.driver.dto.DriverFindDTO;
import com.api.driver.dto.DriverSaveDTO;
import com.api.driver.dto.DriverUpdateDTO;
import com.api.driver.dto.DriverWithCarDTO;
import com.api.driver.entities.Car;
import com.api.driver.entities.Driver;
import com.api.driver.repository.CarRepository;
import com.api.driver.repository.DriverRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private CarRepository carRepository;

	public Long createDriver(DriverSaveDTO driverDto) {
		Driver driver = new Driver();
		driver.setName(driverDto.getName());
		driver.setAge(driverDto.getAge());
		driver.setCars(new ArrayList<Car>());
		driverRepository.save(driver);

		return driver.getId();
	}
	
	public List<DriverWithCarDTO> findAll() {
		List<DriverWithCarDTO> driverWithCars = new ArrayList<DriverWithCarDTO>();
		List<DriverFindDTO> drivers = driverRepository.findDrivers();
		for (DriverFindDTO driverDto : drivers) {;
				List<CarFindDTO> cars = carRepository.findCarByDriverId(driverDto.getId());
				driverWithCars.add(new DriverWithCarDTO(driverDto, cars));
			}
		return driverWithCars;
	}
	
	public DriverWithCarDTO findById(Long id) {
		DriverFindDTO driver = driverRepository.findDriverById(id);
		List<CarFindDTO> cars = carRepository.findCarByDriverId(driver.getId());
		DriverWithCarDTO driverWithCar = new DriverWithCarDTO(driver, cars);
		return driverWithCar;
	}
	
	public void updateDriver(DriverUpdateDTO driverDto) {
		Driver driver = new Driver();
		driver.setId(driverDto.getId());
		driver.setName(driverDto.getName());
		driver.setAge(driverDto.getAge());
		
		if (driverRepository.existsById(driver.getId())) {
			driverRepository.save(driver);
		}
	}
	
	public void deleteDriver(Long id) {
		driverRepository.deleteById(id);
	}
}

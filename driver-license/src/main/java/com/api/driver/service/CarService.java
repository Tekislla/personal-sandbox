package com.api.driver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.driver.dto.CarFindDTO;
import com.api.driver.dto.CarSaveDTO;
import com.api.driver.dto.CarUpdateDTO;
import com.api.driver.dto.CarWithDriverAndLicenseDTO;
import com.api.driver.dto.DriverFindDTO;
import com.api.driver.dto.LicenseForCarFindDTO;
import com.api.driver.entities.Car;
import com.api.driver.entities.Driver;
import com.api.driver.repository.CarRepository;
import com.api.driver.repository.DriverRepository;
import com.api.driver.repository.LicenseRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired LicenseRepository licenseRepository;
	
	public Long buyCar(Long id, CarSaveDTO carDto) {
		Car car = new Car();
		Driver driver = driverRepository.findById(id).get();
		car.setBrand(carDto.getBrand());
		car.setModel(carDto.getModel());
		car.setYear(carDto.getYear());
		car.setPurchaseDate(carDto.getPurchaseDate());
		
		if (driverRepository.existsById(driver.getId())) {
			car.setDriver(driver);
			driver.getCars().add(car);
		} else {
			throw new NullPointerException();
		}
		carRepository.save(car);
		return car.getId();
	}
	
	public void transferCar(Long ownerId, Long newOwnerId, Long carId) {
		Driver newOwner = driverRepository.findById(newOwnerId).get();
		Car car = carRepository.findById(carId).get();
		car.setDriver(newOwner);
		car.getLicense().setDriver(newOwner);
		
		carRepository.save(car);
		
	}
	
	public List<CarWithDriverAndLicenseDTO> findAll() {
		List<CarWithDriverAndLicenseDTO> carWithDriver = new ArrayList<CarWithDriverAndLicenseDTO>();
		List<CarFindDTO> cars = carRepository.findCar();
		for (CarFindDTO car : cars) {
			DriverFindDTO driver = driverRepository.findDriverByCarId(car.getId());
			LicenseForCarFindDTO license = licenseRepository.findLicenseByCarId(car.getId());
			carWithDriver.add(new CarWithDriverAndLicenseDTO(car, driver, license));
		}
		return carWithDriver;
	}
	
	public CarWithDriverAndLicenseDTO findById(Long id) {
		CarFindDTO car = carRepository.findCarById(id);
		DriverFindDTO driver = driverRepository.findDriverByCarId(car.getId());
		LicenseForCarFindDTO license = licenseRepository.findLicenseByCarId(car.getId());
		CarWithDriverAndLicenseDTO carWithDriver = new CarWithDriverAndLicenseDTO(car, driver, license);
		return carWithDriver;
	}
	
	public void updateCar(CarUpdateDTO carDto) {
		Car car = new Car();
		car.setId(carDto.getId());
		car.setBrand(carDto.getBrand());
		car.setModel(carDto.getModel());
		car.setYear(carDto.getYear());
		
		if(carRepository.existsById(car.getId())) {
			carRepository.save(car);
		}
	}
	
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

}
package com.api.driver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.driver.dto.CarFindDTO;
import com.api.driver.entities.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
	
	@Query(value = "SELECT new com.api.driver.dto.CarFindDTO(c.id, c.brand, c.model, c.year, c.purchaseDate) FROM Car c")
	public List<CarFindDTO> findCar();
	
	@Query(value = "SELECT new com.api.driver.dto.CarFindDTO(c.id, c.brand, c.model, c.year, c.purchaseDate) FROM Car c INNER JOIN c.driver d WHERE d.id = ?1")
	public List<CarFindDTO> findCarByDriverId(Long id);
	
	@Query(value = "SELECT new com.api.driver.dto.CarFindDTO(c.id, c.brand, c.model, c.year, c.purchaseDate) FROM Car c WHERE c.id = ?1")
	public CarFindDTO findCarById(Long id);
}

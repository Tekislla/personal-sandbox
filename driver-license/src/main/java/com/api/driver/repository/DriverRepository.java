package com.api.driver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.driver.dto.DriverFindDTO;
import com.api.driver.entities.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
	
	@Query(value = "SELECT new com.api.driver.dto.DriverFindDTO(d.id, d.name, d.age) FROM Driver d")
	public List<DriverFindDTO> findDrivers();

	@Query(value = "SELECT new com.api.driver.dto.DriverFindDTO(d.id, d.name, d.age) FROM Driver d INNER JOIN d.cars c WHERE c.id = ?1")
	public DriverFindDTO findDriverByCarId(Long id);
	
	@Query(value = "SELECT new com.api.driver.dto.DriverFindDTO(d.id, d.name, d.age) FROM Driver d WHERE d.id = ?1")
	public DriverFindDTO findDriverById(Long id);
	
}

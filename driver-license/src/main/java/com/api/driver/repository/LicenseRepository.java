package com.api.driver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.driver.dto.LicenseFindDTO;
import com.api.driver.dto.LicenseForCarFindDTO;
import com.api.driver.entities.License;

public interface LicenseRepository extends CrudRepository<License, Long> {
	
	@Query(value = "SELECT new com.api.driver.dto.LicenseFindDTO(l.id, l.issueDate, l.expirationDate) FROM License l")
	public List<LicenseFindDTO> findLicense();
	
	@Query(value = "SELECT new com.api.driver.dto.LicenseForCarFindDTO(l.id, l.issueDate, l.expirationDate) FROM License l INNER JOIN l.car c WHERE c.id = ?1")
	public LicenseForCarFindDTO findLicenseByCarId(Long id);
	
	@Query(value = "SELECT new com.api.driver.dto.LicenseFindDTO(l.id, l.issueDate, l.expirationDate, d.id, d.name, d.age, c.id, c.brand, c.model, c.year, c.purchaseDate) FROM License l INNER JOIN l.driver d INNER JOIN l.car c")
	public List<LicenseFindDTO> findLicenses();
	
	@Query(value = "SELECT new com.api.driver.dto.LicenseFindDTO(l.id, l.issueDate, l.expirationDate, d.id, d.name, d.age, c.id, c.brand, c.model, c.year, c.purchaseDate) FROM License l INNER JOIN l.driver d INNER JOIN l.car c WHERE l.id = ?1")
	public Optional<LicenseFindDTO> findLicenseById(Long id);
	
}
package com.api.driver.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class License {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate issueDate;
	private LocalDate expirationDate;
	@ManyToOne
	private Driver driver;
	@OneToOne
	private Car car;
	
	public License() {
		
	}
	
	public License(Long id, LocalDate issueDate, LocalDate expirationDate) {
		this.id = id;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
	}

}
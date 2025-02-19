package com.api.driver.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LicenseForCarFindDTO {
	private Long id;
	private LocalDate issueDate;
	private LocalDate expirationDate;
	
	public LicenseForCarFindDTO() {
		
	}
	
	public LicenseForCarFindDTO(Long id, LocalDate issueDate, LocalDate expirationDate) {
		this.id = id;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
	}
	
}
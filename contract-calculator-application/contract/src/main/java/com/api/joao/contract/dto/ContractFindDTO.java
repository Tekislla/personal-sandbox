package com.api.joao.contract.dto;

import lombok.Data;

@Data
public class ContractFindDTO {
	
	private Long id;
	private String description;
	
	public ContractFindDTO() {
		
	}
	
	public ContractFindDTO(Long id, String description) {
		this.id = id;
		this.description = description;
	}
	
}
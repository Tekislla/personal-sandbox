package com.bank.bank.dto;

import lombok.Data;

@Data
public class OwnerSaveDto {
	Long id;
	private String name;
	private int document;
	
	public OwnerSaveDto() {
		
	}

}

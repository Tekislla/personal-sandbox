package com.api.joao.contract.heros;

import lombok.Data;

@Data
public class HerosDetails {
	private String name;
	private String favoriteSpell;
	private String house;
	private String currentLocation;
	
	public HerosDetails() {
		
	}
	
}

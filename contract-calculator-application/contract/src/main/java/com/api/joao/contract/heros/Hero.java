package com.api.joao.contract.heros;

import java.util.List;

import lombok.Data;

@Data
public class Hero {
	private List<HerosDetails> details;
	
	public Hero() {
		
	}
	
}

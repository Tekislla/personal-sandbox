package com.api.joao.contract.dto;

import lombok.Data;

@Data
public class ContractFindBalanceDescriptionDTO {

	private String description;
	private double balance;

	public ContractFindBalanceDescriptionDTO() {

	}

	public ContractFindBalanceDescriptionDTO(String description, double balance) {
		this.description = description;
		this.balance = balance;
	}

}

package com.api.joao.contract.feign;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContractCalculateDTO {
	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private double balance;
	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private double interestRate;
	@NotNull
	private int term;
	@NotNull
	private int baseDays;
	@NotNull
	private LocalDate initialDate;
	
	public ContractCalculateDTO() {
		
	}

}

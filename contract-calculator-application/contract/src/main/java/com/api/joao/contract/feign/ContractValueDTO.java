package com.api.joao.contract.feign;

import java.time.LocalDate;

import com.api.joao.contract.entities.Contract;

import lombok.Data;

@Data
public class ContractValueDTO {
	private Contract contract;
	private LocalDate referenceDate;
	private double balanceDue;
	private double principalBalance;
	private double interestProv;
	private double parcel;
	private double amortization;
	private double interestAccum;
	private double interestPaid;
	
	public ContractValueDTO() {
		
	}

}

package com.bank.operations.classes;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BankOperations {
	private double balance;
	private LocalDate lastOperationDate;
	private LocalDate expirationDate;
	
	public BankOperations() {
		
	}
	
}
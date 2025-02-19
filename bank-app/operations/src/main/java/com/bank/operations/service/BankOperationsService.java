package com.bank.operations.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.bank.operations.classes.BankOperations;

@Service
public class BankOperationsService {
	
	public BankOperations deposit(double value, LocalDate date) {
		BankOperations operation = new BankOperations();
		operation.setLastOperationDate(date);
		operation.setExpirationDate(date.plusYears(3));
		operation.setBalance(operation.getBalance()+value);
		
		return operation;
	}
	
	public BankOperations withdraw(double value, LocalDate date) {
		BankOperations operation = new BankOperations();
		operation.setLastOperationDate(date);
		operation.setExpirationDate(date.plusYears(3));
		operation.setBalance(operation.getBalance()+value);
		
		return operation;
	}

}
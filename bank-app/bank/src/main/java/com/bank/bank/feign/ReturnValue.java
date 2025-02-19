package com.bank.bank.feign;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReturnValue {
	private double balance;
	private LocalDate lastOperationDate;
	private LocalDate expirationDate;

}

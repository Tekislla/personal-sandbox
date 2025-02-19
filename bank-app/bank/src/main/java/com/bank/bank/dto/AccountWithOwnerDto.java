package com.bank.bank.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountWithOwnerDto {
	private Long id;
	private double balance;
	private LocalDate openingDate;
	private LocalDate lastOperationDate;
	private LocalDate expirationDate;
	private OwnerSaveDto accountOwner;

}

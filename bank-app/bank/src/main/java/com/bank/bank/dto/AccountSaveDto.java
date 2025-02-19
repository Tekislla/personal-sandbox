package com.bank.bank.dto;

import java.time.LocalDate;

import com.bank.bank.entities.AccountOwner;

import lombok.Data;

@Data
public class AccountSaveDto {
	private double balance;
	private LocalDate openingDate;
	private AccountOwner owner;
	
	public AccountSaveDto() {
		
	}

}

package com.bank.operations.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.operations.classes.BankOperations;
import com.bank.operations.service.BankOperationsService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping(value = "/operations")
public class BankOperationsController {
	@Autowired
	private BankOperationsService operationsService;
	
	@Timed(value = "deposit.time", description = "Time taken to deposit money")
	@PostMapping(value = "/deposit/{money}/{date}")
	public BankOperations deposit(@PathVariable double money, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return operationsService.deposit(money, date);
	}
	
	@PostMapping(value = "/withdraw/{money}/{date}")
	public BankOperations withdraw(@PathVariable double money, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return operationsService.withdraw(money, date);
	}
	
}
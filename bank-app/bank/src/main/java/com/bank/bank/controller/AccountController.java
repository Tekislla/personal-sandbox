package com.bank.bank.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.dto.AccountSaveDto;
import com.bank.bank.dto.AccountWithOwnerDto;
import com.bank.bank.service.AccountService;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value = "/add-account/{id}")
	public void createAccount(@PathVariable Long id, @RequestBody AccountSaveDto account) {
		accountService.createAccount(id, account);
	}
	
	@GetMapping(value = "/find-all")
	public List<AccountWithOwnerDto> findAll() {
		return accountService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public AccountWithOwnerDto findById(@PathVariable Long id) {
		return accountService.findById(id);
	}
	
	@PostMapping(value = "/update-account/{id}")
	public void updateAccount(@PathVariable Long id, @RequestBody AccountSaveDto account) {
		accountService.updateAccount(id, account);
	}
	
	@PostMapping(value = "/delete-account/{id}")
	public void deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}
	
	@PostMapping(value = "/deposit-money/{id}/{money}/{date}")
	public void depositMoney(@PathVariable Long id, @PathVariable double money, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		accountService.deposit(id, money, date);
	}
	
	@PostMapping(value = "/withdraw-money/{id}/{money}/{date}")
	public void withdrawMoney(@PathVariable Long id, @PathVariable double money, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		accountService.withdraw(id, money, date);
	}

	@PostMapping(value = "/transfer-money/{senderId}/{receiverId}/{money}/{date}")
	public void transferMoney(@PathVariable Long senderId, @PathVariable Long receiverId, @PathVariable double money, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		accountService.transfer(senderId, receiverId,  money, date);
	}
	
}

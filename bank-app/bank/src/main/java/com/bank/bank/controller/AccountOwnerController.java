package com.bank.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.dto.OwnerSaveDto;
import com.bank.bank.entities.AccountOwner;
import com.bank.bank.service.AccountOwnerService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping(value = "/owner")
public class AccountOwnerController {
	@Autowired
	private AccountOwnerService ownerService;
	
	@PostMapping(value = "/add-owner")
	public void createOwner(@RequestBody OwnerSaveDto owner) {
		ownerService.createOwner(owner);
	}
	
	@Timed(value = "find.time", description = "Time taken to return owners")
	@GetMapping(value = "/find-all")
	public List<AccountOwner> findAll() {
		return ownerService.findAll();
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public AccountOwner findById(@PathVariable Long id) {
		return ownerService.findById(id);
	}
	
	@PostMapping(value = "/update-owner/{id}")
	public void updateOwner(@PathVariable Long id, @RequestBody OwnerSaveDto owner) {
		ownerService.updateOwner(id, owner);
	}
	
	@PostMapping(value = "/delete-owner/{id}")
	public void deleteOwner(@PathVariable Long id) {
		ownerService.deleteOwner(id);
	}
	
}

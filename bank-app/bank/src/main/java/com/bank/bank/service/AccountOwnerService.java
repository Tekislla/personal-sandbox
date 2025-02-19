package com.bank.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.dto.OwnerSaveDto;
import com.bank.bank.entities.AccountOwner;
import com.bank.bank.repository.AccountOwnerRepository;

@Service
public class AccountOwnerService {
	@Autowired
	private AccountOwnerRepository ownerRepository;
	
	public void createOwner(OwnerSaveDto ownerDto) {
		AccountOwner owner = new AccountOwner();
		
		owner.setName(ownerDto.getName());
		owner.setDocument(ownerDto.getDocument());
		
		ownerRepository.save(owner);
	}
	
	public List<AccountOwner> findAll() {
		List<AccountOwner> owners = (List<AccountOwner>) ownerRepository.findAll();
		return owners;
	}
	
	public AccountOwner findById(Long id) {
		AccountOwner owner = ownerRepository.findById(id).get();
		return owner;
	}
	
	public void updateOwner(Long id, OwnerSaveDto ownerDto) {
		AccountOwner owner = ownerRepository.findById(id).get();
		owner.setName(ownerDto.getName());
		owner.setDocument(ownerDto.getDocument());
		ownerRepository.save(owner);
	}
	
	public void deleteOwner(Long id) {
		ownerRepository.deleteById(id);
	}

}
package com.bank.bank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.dto.AccountSaveDto;
import com.bank.bank.dto.AccountWithOwnerDto;
import com.bank.bank.dto.OwnerSaveDto;
import com.bank.bank.entities.Account;
import com.bank.bank.entities.AccountOwner;
import com.bank.bank.feign.BankOperationsClient;
import com.bank.bank.feign.ReturnValue;
import com.bank.bank.repository.AccountOwnerRepository;
import com.bank.bank.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountOwnerRepository ownerRepository;
	@Autowired
	BankOperationsClient operations;
	
	
	public void createAccount(Long id, AccountSaveDto accountDto) {
		Account account = new Account();
		AccountOwner owner = ownerRepository.findById(id).get();
		
		account.setBalance(accountDto.getBalance());
		account.setOpeningDate(accountDto.getOpeningDate());
		account.setLastOperationDate(null);
		account.setExpirationDate(accountDto.getOpeningDate().plusYears(3));
		
		if (ownerRepository.existsById(id)) {
			account.setAccountOwner(owner);
			owner.setAccount(account);
		} else {
			throw new NullPointerException();
		}
		
		accountRepository.save(account);		
	}
	
	public List<AccountWithOwnerDto> findAll() {
		List<AccountWithOwnerDto> accountsWithOwner = new ArrayList<AccountWithOwnerDto>();
		List<Account> accounts = (List<Account>) accountRepository.findAll();
		
		for(Account auxAcc : accounts) {
			OwnerSaveDto owner = new OwnerSaveDto();
			AccountWithOwnerDto account = new AccountWithOwnerDto();
			owner.setId(auxAcc.getAccountOwner().getId());
			owner.setName(auxAcc.getAccountOwner().getName());
			owner.setDocument(auxAcc.getAccountOwner().getDocument());
			account.setId(auxAcc.getId());
			account.setBalance(auxAcc.getBalance());
			account.setOpeningDate(auxAcc.getOpeningDate());
			account.setLastOperationDate(auxAcc.getLastOperationDate());
			account.setExpirationDate(auxAcc.getExpirationDate());
			account.setAccountOwner(owner);
			
			accountsWithOwner.add(account);
		}
				
		return accountsWithOwner;
	}
	
	public AccountWithOwnerDto findById(Long id) {
		OwnerSaveDto owner = new OwnerSaveDto();
		AccountWithOwnerDto accountWithOwner = new AccountWithOwnerDto();
		Account account = accountRepository.findById(id).get();
		
		owner.setId(account.getAccountOwner().getId());
		owner.setName(account.getAccountOwner().getName());
		owner.setDocument(account.getAccountOwner().getDocument());
		accountWithOwner.setId(account.getId());
		accountWithOwner.setBalance(account.getBalance());
		accountWithOwner.setOpeningDate(account.getOpeningDate());
		accountWithOwner.setLastOperationDate(account.getLastOperationDate());
		accountWithOwner.setExpirationDate(account.getExpirationDate());
		accountWithOwner.setAccountOwner(owner);
		
		return accountWithOwner;
	}
	
	public void updateAccount(Long id, AccountSaveDto accountDto) {
		Account account = accountRepository.findById(id).get();
		account.setBalance(accountDto.getBalance());
		account.setOpeningDate(accountDto.getOpeningDate());
		
		accountRepository.save(account);
	}
	
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);
	}
	
	public void deposit(Long id, double money, LocalDate date) {
		Account account = accountRepository.findById(id).get();
		ReturnValue returnValue = operations.deposit(money, date);
		
		account.setBalance(account.getBalance() + returnValue.getBalance());
		account.setLastOperationDate(returnValue.getLastOperationDate());
		account.setExpirationDate(returnValue.getExpirationDate());
		
		accountRepository.save(account);
	}
	
	public void withdraw(Long id, double money, LocalDate date) {
		Account account = accountRepository.findById(id).get();
		ReturnValue returnValue = operations.withdraw(money, date);
		
		account.setBalance(account.getBalance() - returnValue.getBalance());
		account.setLastOperationDate(returnValue.getLastOperationDate());
		account.setExpirationDate(returnValue.getExpirationDate());
		
		accountRepository.save(account);
	}
	
	public void transfer(Long senderId, Long receiverId, double money, LocalDate date) {
		Account sender = ownerRepository.findById(senderId).get().getAccount();
		Account receiver = ownerRepository.findById(receiverId).get().getAccount();
		ReturnValue returnSenderWithdraw = operations.withdraw(money, date);
		ReturnValue returnReceiverDeposit = operations.deposit(money, date);
		
		sender.setBalance(sender.getBalance() - returnSenderWithdraw.getBalance());
		sender.setLastOperationDate(returnSenderWithdraw.getLastOperationDate());
		sender.setExpirationDate(returnSenderWithdraw.getExpirationDate());
		
		receiver.setBalance(receiver.getBalance() + returnReceiverDeposit.getBalance());
		receiver.setLastOperationDate(returnReceiverDeposit.getLastOperationDate());
		receiver.setExpirationDate(returnReceiverDeposit.getExpirationDate());
		
		accountRepository.save(sender);
		accountRepository.save(receiver);
	}
	
}
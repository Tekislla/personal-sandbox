package com.bank.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.bank.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}

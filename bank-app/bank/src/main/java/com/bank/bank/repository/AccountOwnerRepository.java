package com.bank.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.bank.entities.AccountOwner;

public interface AccountOwnerRepository extends CrudRepository<AccountOwner, Long> {

}

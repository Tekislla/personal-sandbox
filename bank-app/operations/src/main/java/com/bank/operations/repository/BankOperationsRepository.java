package com.bank.operations.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.operations.classes.BankOperations;

public interface BankOperationsRepository extends CrudRepository<BankOperations, Long> {

}
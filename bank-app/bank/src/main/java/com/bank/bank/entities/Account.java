package com.bank.bank.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double balance;
	private LocalDate openingDate;
	private LocalDate lastOperationDate;
	private LocalDate expirationDate;
	@JsonBackReference
	@OneToOne
	private AccountOwner accountOwner;
	
	public Account() {
		
	}

}
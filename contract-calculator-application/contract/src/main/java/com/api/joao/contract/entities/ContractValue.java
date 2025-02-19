package com.api.joao.contract.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table
public class ContractValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "contract_id")
	private Contract contract;
	private LocalDate referenceDate;
	private double balanceDue;
	private double principalBalance;
	private double interestProv;
	private double parcel;
	private double amortization;
	private double interestAccum;
	private double interestPaid;
	
	public ContractValue() {
		
	}
	
}
package com.api.joao.contract.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonManagedReference
	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
	private List<ContractValue> contractValue;

	private String description;
	private double balance;
	private double interestRate;
	private int term;
	private int baseDays;
	private LocalDate initialDate;
	private LocalDate finalDate;
	
	public Contract() {

	}

}

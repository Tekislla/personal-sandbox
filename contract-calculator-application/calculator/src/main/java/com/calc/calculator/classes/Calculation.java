package com.calc.calculator.classes;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Calculation {
	private double balance;
	private double interestRate;
	private int term;
	private int baseDays;
	private LocalDate initialDate;
	
	public Calculation() {
		
	}

	public Calculation(double balance, double interestRate, int term, int baseDays, LocalDate initialDate) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.initialDate = initialDate;
		this.term = term;
		this.baseDays = baseDays;
	}
	
}
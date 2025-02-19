package com.calc.calculator.classes;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CalculatedValue {
	private LocalDate referenceDate;
	private double parcel;
	private double balanceDue;
	private double amortization;
	private double principalBalance;
	private double interestProv;
	private double interestAccum;
	private double interestPaid;
	
	public CalculatedValue() {
		
	}
	
	public CalculatedValue(LocalDate paymentDate, double parcel, double balanceDue, double amortization, double principalBalance, double interestProv, double interestAccum, double interestPaid) {
		this.referenceDate = paymentDate;
		this.parcel = parcel;
		this.balanceDue = balanceDue;
		this.amortization = amortization;
		this.principalBalance = principalBalance;
		this.interestProv = interestProv;
		this.interestAccum = interestAccum;
		this.interestPaid = interestPaid;
	}
	
}

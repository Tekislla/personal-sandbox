package com.calc.calculator.thiago;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CalculatedValue implements Comparable<CalculatedValue> {
	
	
	
	private LocalDate referenceDate;
	private BigDecimal balance = BigDecimal.ZERO;
	private BigDecimal principalBalance = BigDecimal.ZERO;
	private BigDecimal installment = BigDecimal.ZERO;
	private BigDecimal amortizationValue = BigDecimal.ZERO;
	private BigDecimal interestProv = BigDecimal.ZERO;
	private BigDecimal interestAccum = BigDecimal.ZERO;
	private BigDecimal interestPaid = BigDecimal.ZERO;
	private boolean payment = false;


	public CalculatedValue() {
		// TODO Auto-generated constructor stub
	}
	
	public CalculatedValue(LocalDate referenceDate, boolean payment) {
		this.referenceDate = referenceDate;
		this.payment = payment;
	}
	

	
	@Override
	public int compareTo(CalculatedValue o) {
		
		
		
		return 0;
	}
	
	


}

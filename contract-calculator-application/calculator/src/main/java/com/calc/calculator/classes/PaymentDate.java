package com.calc.calculator.classes;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PaymentDate {
	int payment;
	LocalDate paymentDate;
	private int days;
	
	public PaymentDate() {
		
	}

	public PaymentDate(int payment, LocalDate paymentDate, int days) {
		this.payment = payment;
		this.paymentDate = paymentDate;
		this.days = days;
	}
}
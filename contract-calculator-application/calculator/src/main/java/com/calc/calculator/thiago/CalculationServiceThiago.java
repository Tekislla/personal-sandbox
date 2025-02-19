package com.calc.calculator.thiago;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.calc.calculator.classes.Calculation;

@Service
public class CalculationServiceThiago {
	
	public List<CalculatedValue> calculate(Calculation params) {
		
		List<CalculatedValue> calculatedValues = new ArrayList<CalculatedValue>();
		
		//gerar datas
		
		LocalDate initDate = params.getInitialDate();
		LocalDate endDate = initDate.plusMonths(params.getTerm());
		LocalDate currentDate = initDate;
		int baseDay = initDate.getDayOfMonth();
		
		boolean wasEndMonth = false;
		
		calculatedValues.add(new CalculatedValue(initDate, false));
		
		while(currentDate.isBefore(endDate))
		{
			if(wasEndMonth) {
				 currentDate = currentDate.withDayOfMonth(baseDay).plusMonths(1);
				 calculatedValues.add(new CalculatedValue(currentDate, true));
				 wasEndMonth = false;
			} else {
				 currentDate = currentDate.with(TemporalAdjusters.lastDayOfMonth());
				calculatedValues.add(new CalculatedValue(currentDate, false));
				wasEndMonth = true;
			}			
		} 
		
		CalculatedValue previousValue = new CalculatedValue();
		
		BigDecimal amortizationValue = new BigDecimal(params.getBalance() / params.getBalance());
	
		for (CalculatedValue calculatedValue : calculatedValues) {
			
			if(calculatedValue.getReferenceDate().compareTo(initDate) == 0) {
				calculatedValue.setBalance(new BigDecimal(params.getBalance()));
				calculatedValue.setPrincipalBalance(new BigDecimal(params.getBalance()));
			} else {
				
				int days = Period.between(previousValue.getReferenceDate(), calculatedValue.getReferenceDate()).getDays();
				BigDecimal interestRate = calculateRate(days, params.getBaseDays(), params.getInterestRate());
				
				
				BigDecimal interestProv = previousValue.getPrincipalBalance().multiply(interestRate);
				calculatedValue.setInterestProv(interestProv);
				
				if(calculatedValue.isPayment()) {
					calculatedValue.setAmortizationValue(amortizationValue);
					calculatedValue.setInterestPaid(previousValue.getInterestAccum().add(calculatedValue.getInterestProv()));					
				} 
				
				
				BigDecimal interestAccum = previousValue.getInterestAccum().add(calculatedValue.getInterestProv()).subtract(calculatedValue.getInterestPaid());
				calculatedValue.setInterestAccum(interestAccum);
				
				
				BigDecimal principalBalance = previousValue.getPrincipalBalance().subtract(calculatedValue.getAmortizationValue());
				calculatedValue.setPrincipalBalance(principalBalance);
				
				BigDecimal balance = principalBalance.add(calculatedValue.getInterestAccum());
				calculatedValue.setBalance(balance);
				
				BigDecimal installment = calculatedValue.getAmortizationValue().add(calculatedValue.getInterestPaid());
				calculatedValue.setInstallment(installment);
			}
			
			
			
			previousValue = calculatedValue;
			
		}
		
		for (CalculatedValue calculatedValue : calculatedValues) {
			calculatedValue.setInstallment(calculatedValue.getInstallment().setScale(2, RoundingMode.HALF_UP));
			calculatedValue.setBalance(calculatedValue.getBalance().setScale(2, RoundingMode.HALF_UP));
			calculatedValue.setAmortizationValue(calculatedValue.getAmortizationValue().setScale(2, RoundingMode.HALF_UP));
			calculatedValue.setPrincipalBalance(calculatedValue.getPrincipalBalance().setScale(2, RoundingMode.HALF_UP));
			calculatedValue.setInterestProv(calculatedValue.getInterestProv().setScale(2, RoundingMode.HALF_UP));
			calculatedValue.setInterestAccum(calculatedValue.getInterestAccum().setScale(2, RoundingMode.HALF_UP));
			calculatedValue.setInterestPaid(calculatedValue.getInterestPaid().setScale(2, RoundingMode.HALF_UP));
		}
		
		
		return calculatedValues;
		
		
	}
	
	public static BigDecimal calculateRate(int days, int baseDays, double baseRate) {
		
		BigDecimal rate = BigDecimal.ZERO;
		
		double base = (baseRate / 100.0) + 1;
		double expoent = (double) days / baseDays;
		rate = new BigDecimal(Math.pow(base, expoent));
		rate = rate.subtract(BigDecimal.ONE);
		rate = rate.setScale(16, RoundingMode.HALF_UP);
		System.out.println(rate.doubleValue());
		
		return rate;
		
	}
	
	public static  BigDecimal calculateAccumRate(BigDecimal currentAccum, BigDecimal rate) {
		BigDecimal accumRate = currentAccum.add(BigDecimal.ONE);		
		accumRate = accumRate.multiply(rate.add(BigDecimal.ONE));
		accumRate = accumRate.subtract(BigDecimal.ONE);
		accumRate = accumRate.setScale(16, RoundingMode.HALF_UP);
		return accumRate;
	   
	}
	
	

}

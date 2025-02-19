package com.calc.calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.calc.calculator.classes.Calculation;
import com.calc.calculator.classes.PaymentDate;
import com.calc.calculator.classes.CalculatedValue;

@Service
public class CalculationService {

	public List<CalculatedValue> calculate(Calculation calculo) {
		// Declaração de variáveis recebidas
		int term = calculo.getTerm();
		int baseDays = calculo.getBaseDays();
		LocalDate initialDate = calculo.getInitialDate();
		LocalDate finalDate = initialDate.plusMonths(term);
		double interestRate = calculo.getInterestRate();
		double balance = calculo.getBalance() / term;
		
		// Declaração de variáveis de retorno
		int days = 0;
		double paidBalance = 0;
		double balanceDue = calculo.getBalance();
		double amortization = 0;
		double princibalBalance = calculo.getBalance();
		double interestRateMonth = 0;
		double interestRateAccum = 0;
		double interestProv = 0;
		double interestAccum = 0;
		double interestPaid = 0;

		// Listas
		List<PaymentDate> paymentDates = new ArrayList<PaymentDate>();
		List<CalculatedValue> calculatedValues = new ArrayList<CalculatedValue>();

		// Calculando final do mês da primeira data
		LocalDate endMonth = initialDate.with(TemporalAdjusters.lastDayOfMonth());

		// Calculando dias entre a data inicial e o final do mês
		Period initialPeriod = Period.between(initialDate, endMonth);
		days = initialPeriod.getDays();
		
		// Adiciona as primeiras datas na lista
		paymentDates.add(new PaymentDate(0, initialDate, 0));
		paymentDates.add(new PaymentDate(0, endMonth, days));

		
		// Loop para adicionar os meses e armazenar numa lista
		for (int i = 1; i <= term; i++) {
			// Adiciona 1 mês a cada loop
			LocalDate paymentDate = initialDate.plusMonths(i);
			
			// Para cada mês, adiciona o fim do mês
			LocalDate endOfMonth = paymentDate.with(TemporalAdjusters.lastDayOfMonth());

			// Calcula diferença de dias entre a data do pagamento e o fim do mês
			Period mensalPeriod = Period.between(paymentDate, endOfMonth);
			days = mensalPeriod.getDays();
			
			// Adiciona as datas de pagamento na lista de datas
			paymentDates.add(new PaymentDate(1, paymentDate, paymentDate.getDayOfMonth()));
			
			// Faz a validação com a última parcela
			if (endOfMonth.compareTo(finalDate) < 0) {
				paymentDates.add(new PaymentDate(0, endOfMonth, days));
			}
		}
		
		// Loop processando o cálculo para cada mês
		for (PaymentDate paymentDate : paymentDates) {
			// Cálculo taxa de juros mensal
			double base = 1 + (interestRate/100);
			double exp = (double) paymentDate.getDays() / baseDays;
			double calc = Math.pow(base, exp);
			interestRateMonth = calc - 1;
			
			// Cálculo taxa de juros Acumulada e juros provisionado
			interestRateAccum = ((interestRateAccum + 1) * (interestRateMonth + 1)) - 1;
			interestProv = interestRateAccum * princibalBalance;
			
			// Validação se há pagamento
			if (paymentDate.getPayment() == 1) {
				amortization = balance;
				interestPaid = interestProv + interestAccum;
				paidBalance = amortization + interestPaid;
				princibalBalance -= amortization;
				interestRateAccum = 0;
			} else {
				interestPaid = 0;
				paidBalance = 0;
				amortization = 0;
			}
			
			interestAccum += interestProv - interestPaid;
			balanceDue = princibalBalance + interestAccum;
			
			// Setando os valores em uma nova variável
			CalculatedValue calculatedValue = new CalculatedValue();
			calculatedValue.setAmortization(amortization);
			calculatedValue.setReferenceDate(paymentDate.getPaymentDate());
			calculatedValue.setInterestAccum(interestAccum);
			calculatedValue.setInterestPaid(interestPaid);
			calculatedValue.setInterestProv(interestProv);
			calculatedValue.setParcel(paidBalance);
			calculatedValue.setBalanceDue(balanceDue);
			calculatedValue.setPrincipalBalance(princibalBalance);
			
			// Adicionando a nova variável com os valores setados na lista
			calculatedValues.add(calculatedValue);
		}
		
		// Formata os valores para 2 casas decimais
		for (CalculatedValue formatValues : calculatedValues) {
			BigDecimal formatValue = new BigDecimal(formatValues.getInterestAccum());
			formatValues.setInterestAccum(formatValue.setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			formatValue = new BigDecimal(formatValues.getInterestProv());
			formatValues.setInterestProv(formatValue.setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			formatValue = new BigDecimal(formatValues.getInterestPaid());
			formatValues.setInterestPaid(formatValue.setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			formatValue = new BigDecimal(formatValues.getParcel());
			formatValues.setParcel(formatValue.setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			formatValue = new BigDecimal(formatValues.getBalanceDue());
			formatValues.setBalanceDue(formatValue.setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			formatValue = new BigDecimal(formatValues.getPrincipalBalance());
			formatValues.setPrincipalBalance(formatValue.setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			formatValue = new BigDecimal(formatValues.getAmortization());
			formatValues.setAmortization(formatValue.setScale(2, RoundingMode.HALF_UP).doubleValue());
		}
		
		// Retorno da lista formatada
		return calculatedValues;
		
		
	}
}
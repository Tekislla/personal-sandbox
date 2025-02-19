package com.calc.calculator.thiago;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.calc.calculator.classes.Calculation;

@SpringBootTest
public class CalculationServiceTest {

	public List<CalculatedValue> loadBaseContract() throws IOException {
		List<CalculatedValue> baseValues = new ArrayList<CalculatedValue>();

		FileInputStream file = new FileInputStream(
				new File(this.getClass().getClassLoader().getResource("baseValues.xlsx").getFile()));
		Workbook workbook = new XSSFWorkbook(file);

		Sheet sheet = workbook.getSheetAt(0);

		for (Row row : sheet) {

			CalculatedValue value = new CalculatedValue();
			value.setReferenceDate(
					row.getCell(0).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			int payment = (int) row.getCell(1).getNumericCellValue();
			value.setPayment(payment == 1 ? true : false);
			value.setInstallment(new BigDecimal(row.getCell(2).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
			value.setBalance(new BigDecimal(row.getCell(3).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
			value.setAmortizationValue(new BigDecimal(row.getCell(4).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
			value.setPrincipalBalance(new BigDecimal(row.getCell(5).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
			value.setInterestProv(new BigDecimal(row.getCell(6).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
			value.setInterestAccum(new BigDecimal(row.getCell(7).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
			value.setInterestPaid(new BigDecimal(row.getCell(8).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
			baseValues.add(value);

		}

		return baseValues;

	}

	@Test
	public void testValues() throws IOException {

		List<CalculatedValue> baseValues = loadBaseContract();

		CalculationServiceThiago service = new CalculationServiceThiago();

		Calculation params = new Calculation();//(100000, 4.2, LocalDate.of(2021, 11, 8), 6, 360);

		List<CalculatedValue> values = service.calculate(params);

		assertEquals(values.size(), 13);
		
		for(int index=0; index < values.size(); index++) {
			CalculatedValue original = baseValues.get(index);
			CalculatedValue calculated = values.get(index);
			
			assertEquals(original.getReferenceDate(), calculated.getReferenceDate());
			assertEquals(original.isPayment(), calculated.isPayment());
			assertEquals(original.getInstallment(), calculated.getInstallment());
			assertEquals(original.getBalance(), calculated.getBalance());
			assertEquals(original.getAmortizationValue(), calculated.getAmortizationValue());
			assertEquals(original.getPrincipalBalance(), calculated.getPrincipalBalance());
			assertEquals(original.getInterestProv(), calculated.getInterestProv());
			assertEquals(original.getInterestAccum(), calculated.getInterestAccum());
			assertEquals(original.getInterestPaid(), calculated.getInterestPaid());
		}

	}
	
	@Test
	public void testRate() {
		BigDecimal calculatedRate = CalculationServiceThiago.calculateRate(23, 360, 4.2);
		BigDecimal expectedRate = new BigDecimal(0.00263197061533349);
		expectedRate = expectedRate.setScale(16, RoundingMode.HALF_UP);
		assertEquals(expectedRate, calculatedRate);
	}

}

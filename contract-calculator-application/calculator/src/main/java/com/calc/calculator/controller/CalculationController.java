package com.calc.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calc.calculator.classes.CalculatedValue;
import com.calc.calculator.classes.Calculation;
import com.calc.calculator.classes.ReturnList;
import com.calc.calculator.service.CalculationService;

@RestController
@RequestMapping(value = "/calc")
public class CalculationController {
	@Autowired
	private CalculationService calculationService;
	@Value("${eureka.instance.instance-id}")
	private String instanceValue;
	
	
	
	@PostMapping(value = "/calculate")
	public ReturnList calculo(@RequestBody Calculation calculation) {
		List<CalculatedValue> result = calculationService.calculate(calculation);
		ReturnList returnList = new ReturnList();
		returnList.setCalculatedValues(result);
		System.out.println(instanceValue);
		
		return returnList;
	}
}
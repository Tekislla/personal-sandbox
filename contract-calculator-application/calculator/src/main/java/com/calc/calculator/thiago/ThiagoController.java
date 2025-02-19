package com.calc.calculator.thiago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calc.calculator.classes.Calculation;

@RestController
@RequestMapping(value = "/thiago")
public class ThiagoController {
	
	@Autowired
	private CalculationServiceThiago calculationService;
	
	@PostMapping(value = "/calculate")
	public void calculate(@RequestBody Calculation params ) {
		calculationService.calculate(params);
	}

}

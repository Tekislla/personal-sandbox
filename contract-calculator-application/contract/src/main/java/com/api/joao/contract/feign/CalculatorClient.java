package com.api.joao.contract.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "CALCULATOR", path = "/calc", configuration = FeignConfig.class)
public interface CalculatorClient {
	
	@PostMapping(value = "/calculate")
	public ReturnList calculo(@RequestBody ContractCalculateDTO contract);

}

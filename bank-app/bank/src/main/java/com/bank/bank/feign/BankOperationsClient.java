package com.bank.bank.feign;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(value = "OPERATIONS", path = "/operations", configuration = FeignConfig.class)
public interface BankOperationsClient {
		
	@PostMapping(value = "/operations/deposit/{money}/{date}")
	public ReturnValue deposit(@PathVariable double money, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
	
	@PostMapping(value = "/operations/withdraw/{money}/{date}")
	public ReturnValue withdraw(@PathVariable double money, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
	
}
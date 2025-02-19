package com.bank.bank.feign;

import org.springframework.context.annotation.Bean;

public class FeignConfig {
	
	@Bean
	public FeignInterceptor interceptor() {
		return new FeignInterceptor();
	}
	
}

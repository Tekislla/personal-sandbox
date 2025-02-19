package com.api.joao.contract.feign;

import org.springframework.context.annotation.Bean;

public class FeignConfig {
	
	@Bean
	public FeignInterceptor interceptor() {
		return new FeignInterceptor();
	}
	
}

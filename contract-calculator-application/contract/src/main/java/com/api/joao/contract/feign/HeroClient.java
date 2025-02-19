package com.api.joao.contract.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.joao.contract.heros.Hero;

@FeignClient(name = "heros-test", url = "http://10.77.0.95:8080/info")
public interface HeroClient {
	
	@GetMapping(value = "/retrieve")
	public Hero getHeros(@RequestParam Integer quantity);

}

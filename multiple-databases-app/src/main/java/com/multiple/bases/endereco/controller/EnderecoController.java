package com.multiple.bases.endereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiple.bases.endereco.database.entity.Endereco;
import com.multiple.bases.endereco.service.EnderecoService;

@RestController
@RequestMapping(value = "/address")
public class EnderecoController {
	@Autowired
	EnderecoService service;
	
	@PostMapping(value = "/save")
	public void saveAddress(@RequestBody Endereco address) {
		service.saveAddress(address);
	}
	
	@GetMapping(value = "/find")
	public List<Endereco> findAll() {
		return service.findAll();
	}

}

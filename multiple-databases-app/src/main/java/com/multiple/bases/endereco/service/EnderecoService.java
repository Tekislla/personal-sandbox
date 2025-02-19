package com.multiple.bases.endereco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiple.bases.endereco.database.entity.Endereco;
import com.multiple.bases.endereco.database.repo.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public void saveAddress(Endereco address) {
		enderecoRepository.save(address);
	}
	
	public List<Endereco> findAll() {
		return (List<Endereco>)enderecoRepository.findAll();
	}
	
	
}

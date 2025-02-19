package com.api.joao.contract.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.joao.contract.dto.ContractFindBalanceDescriptionDTO;
import com.api.joao.contract.dto.ContractFindDTO;
import com.api.joao.contract.dto.ContractSaveDTO;
import com.api.joao.contract.dto.ContractUpdateDTO;
import com.api.joao.contract.entities.Contract;
import com.api.joao.contract.heros.Hero;
import com.api.joao.contract.heros.HerosDetails;
import com.api.joao.contract.service.ContractService;

@RestController
@RequestMapping(value = "/contract")
public class ContractController {

	@Autowired
	private ContractService contractService;
	
	@PostMapping(value = "/calculate-contract")
	public void calculateContract(@RequestBody ContractSaveDTO contractDto) {
		contractService.calculateContract(contractDto);
	}
	
	@GetMapping(value = "/find-heros")
	public List<HerosDetails> findHeros() {
		return contractService.findHeros();
	}
	
	@GetMapping(value = "/get-heros")
	public Hero getHeros() {
		return contractService.getHeros();
	}
	
	@Secured("ROLE_gamer")
	@PostMapping(value = "/add-contract")
	public String addContract(@RequestBody @Valid ContractSaveDTO contract) {
		return contractService.addContract(contract);
	}

	@GetMapping(value = "/find-all")
	public List<Contract> findAll() {
		return contractService.findAll();
	}

	@GetMapping(value = "/search/{id}")
	public Contract searchContract(@PathVariable Long id) {
		return contractService.searchContract(id);
	}

	@PostMapping(value = "/delete-contract/{id}")
	public void deleteContract(@PathVariable Long id) {
		contractService.deleteContract(id);
	}

	@PostMapping(value = "/update-contract")
	public String updateContract(@RequestBody @Valid ContractUpdateDTO contract) {
		return contractService.updateContract(contract);
	}

	@GetMapping(value = "/find-by-description/{description}")
	public Contract findByDescription(@PathVariable String description) {
		return contractService.findByDescription(description);
	}

	@GetMapping(value = "/find-idate-gte/{initialDate}")
	public List<Contract> findByInitialDateGreaterThanEqual(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate) {
		return contractService.findByInitialDateGreaterThanEqual(initialDate);
	}

	@GetMapping(value = "/find-idate-between/{startDate}/{endDate}")
	public List<Contract> findByInitialDateBetween(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		return contractService.findByInitialDateBetween(startDate, endDate);
	}

	@GetMapping(value = "/find-value-greater/{value}")
	public List<Contract> findByValueGreaterThan(@PathVariable Float value) {
		return contractService.findByValueGreaterThan(value);
	}

	@GetMapping(value = "/find-idlte-fdgte/{initialDate}/{finalDate}")
	public List<Contract> findByInitialDateLessThanEqualAndFinalDateGreaterThanEqual(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) {
		return contractService.findByInitialDateLessThanEqualAndFinalDateGreaterThanEqual(initialDate, finalDate);
	}
	
	@GetMapping (value = "/catch-id")
	public List<ContractFindDTO> catchId() {
		return contractService.catchId();
	}
	
	@GetMapping (value = "/find-value-description")
	public List<ContractFindBalanceDescriptionDTO> findValueDescription() {
		return contractService.findValueDescription();
	}

}

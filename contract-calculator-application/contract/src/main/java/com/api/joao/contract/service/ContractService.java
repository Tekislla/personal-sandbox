package com.api.joao.contract.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.joao.contract.dto.ContractFindBalanceDescriptionDTO;
import com.api.joao.contract.dto.ContractFindDTO;
import com.api.joao.contract.dto.ContractSaveDTO;
import com.api.joao.contract.dto.ContractUpdateDTO;
import com.api.joao.contract.entities.Contract;
import com.api.joao.contract.entities.ContractValue;
import com.api.joao.contract.exception.ContractInitialDateGreaterException;
import com.api.joao.contract.exception.ContractNotFoundException;
import com.api.joao.contract.feign.CalculatorClient;
import com.api.joao.contract.feign.ContractCalculateDTO;
import com.api.joao.contract.feign.ContractValueDTO;
import com.api.joao.contract.feign.HeroClient;
import com.api.joao.contract.feign.ReturnList;
import com.api.joao.contract.heros.Hero;
import com.api.joao.contract.heros.HerosDetails;
import com.api.joao.contract.repository.ContractRepository;

@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private CalculatorClient calculatorClient;
	
	@Autowired
	HeroClient heroClient;
	
	public List<ContractValueDTO> calculateContract(ContractSaveDTO contractDto) {
		RestTemplate restTemplate = new RestTemplate();
		String calculateContractUrl = "http://localhost:7080/calc/calculate";
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
				
	    ContractCalculateDTO contract = new ContractCalculateDTO();
		contract.setBalance(contractDto.getBalance());
		contract.setBaseDays(contractDto.getBaseDays());
		contract.setInitialDate(contractDto.getInitialDate());
		contract.setTerm(contractDto.getTerm());
		contract.setInterestRate(contractDto.getInterestRate());
		 
		JSONObject jsonObject = new JSONObject(contract);
		String json = jsonObject.toString();
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		ReturnList contractAsString = restTemplate.postForObject(calculateContractUrl, request, ReturnList.class);

		return contractAsString.getCalculatedValues();
	}
	
	public List<HerosDetails> findHeros() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://10.77.0.95:8080/info/retrieve?quantity=10";
		HerosDetails[] heroDetails = restTemplate.getForObject(fooResourceUrl, HerosDetails[].class);
		return Arrays.asList(heroDetails);
		
	}
	
	public Hero getHeros() {
		return heroClient.getHeros(10);
		
	}
	
	
	public String addContract(ContractSaveDTO contractDTO) {
		Contract contract = new Contract();
		
		contract.setDescription(contractDTO.getDescription());
		contract.setFinalDate(contractDTO.getInitialDate().plusMonths(contractDTO.getTerm()));
		contract.setInitialDate(contractDTO.getInitialDate());
		contract.setBalance(contractDTO.getBalance());
		contract.setInterestRate(contractDTO.getInterestRate());
		contract.setBaseDays(contractDTO.getBaseDays());
		contract.setTerm(contractDTO.getTerm());

		ContractCalculateDTO calculateDto = new ContractCalculateDTO();
		
		calculateDto.setBalance(contractDTO.getBalance());
		calculateDto.setBaseDays(contractDTO.getBaseDays());
		calculateDto.setInitialDate(contractDTO.getInitialDate());
		calculateDto.setTerm(contractDTO.getTerm());
		calculateDto.setInterestRate(contractDTO.getInterestRate());
		
		ReturnList returnList = calculatorClient.calculo(calculateDto);
		
		List<ContractValue> contractValueList = new ArrayList<ContractValue>();
		
		for(ContractValueDTO calculatedValue : returnList.getCalculatedValues()) {
			ContractValue contractValue = new ContractValue();
			
			contractValue.setAmortization(calculatedValue.getAmortization());
			contractValue.setBalanceDue(calculatedValue.getBalanceDue());
			contractValue.setParcel(calculatedValue.getParcel());
			contractValue.setInterestAccum(calculatedValue.getInterestAccum());
			contractValue.setInterestPaid(calculatedValue.getInterestPaid());
			contractValue.setInterestProv(calculatedValue.getInterestProv());
			contractValue.setPrincipalBalance(calculatedValue.getPrincipalBalance());
			contractValue.setReferenceDate(calculatedValue.getReferenceDate());
			contractValue.setContract(contract);
			
			contractValueList.add(contractValue);
		}
		
		contract.setContractValue(contractValueList);
		
		validateException(contract);
		contractRepository.save(contract);
		
		String str = "Contrato adicionado! ID: ";
		return str + contract.getId();
	}

	public List<Contract> findAll() {
		List<Contract> contracts = (List<Contract>) contractRepository.findAll();

		return contracts;
	}

	public Contract searchContract(Long id) {
		Optional<Contract> contract = contractRepository.findById(id);
		if (contract.isEmpty()) {
			throw new ContractNotFoundException();
		}

		return contract.get();
	}

	public String deleteContract(Long id) {
		contractRepository.deleteById(id);
		String str = "Contrato deletado! ID: ";
		return str + id;
	}

	public String updateContract(ContractUpdateDTO contractDTO) {
		Contract contract = new Contract();
		contract.setId(contractDTO.getId());
		contract.setDescription(contractDTO.getDescription());
		contract.setFinalDate(contractDTO.getInitialDate().plusMonths(contractDTO.getTerm()));
		contract.setInitialDate(contractDTO.getInitialDate());
		contract.setBalance(contractDTO.getBalance());
		contract.setInterestRate(contractDTO.getInterestRate());
		contract.setBaseDays(contractDTO.getBaseDays());
		contract.setTerm(contractDTO.getTerm());

		if (!contractRepository.existsById(contractDTO.getId())) {
			throw new ContractNotFoundException();
		} else {
			validateException(contract);
			contractRepository.save(contract);
		}
		
		String str = "Contrato atualizado! ID: ";
		return str + contract.getId();
	}

	public Contract findByDescription(String description) {
		Contract contract = contractRepository.findByDescription(description);
		return contract;
	}

	public List<Contract> findByInitialDateGreaterThanEqual(LocalDate initialDate) {
		List<Contract> contract = contractRepository.findByInitialDateGreaterThanEqual(initialDate);
		return contract;
	}

	public List<Contract> findByInitialDateBetween(LocalDate startDate, LocalDate endDate) {
		List<Contract> contract = contractRepository.findByInitialDateBetween(startDate, endDate);
		return contract;
	}

	public List<Contract> findByValueGreaterThan(double balance) {
		List<Contract> contract = contractRepository.findByBalanceGreaterThan(balance);
		return contract;
	}

	public List<Contract> findByInitialDateLessThanEqualAndFinalDateGreaterThanEqual(LocalDate initialDate, LocalDate finalDate) {
		List<Contract> contract = contractRepository.findByInitialDateLessThanEqualAndFinalDateGreaterThanEqual(initialDate, finalDate);
		return contract;
	}

	public List<ContractFindDTO> catchId() {
		return contractRepository.findIdAndDescription();
	}

	public List<ContractFindBalanceDescriptionDTO> findValueDescription() {
		return contractRepository.findValueDescription();
	}

	public void validateException(Contract contract) {
		if (contract.getInitialDate().compareTo(contract.getFinalDate()) >= 0) {
			throw new ContractInitialDateGreaterException();
		}
	}
}
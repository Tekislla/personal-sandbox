package com.api.joao.contract.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.joao.contract.dto.ContractFindDTO;
import com.api.joao.contract.dto.ContractFindBalanceDescriptionDTO;
import com.api.joao.contract.entities.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

	public Contract findByDescription(String description);

	public List<Contract> findByInitialDateGreaterThanEqual(LocalDate initialDate);

	public List<Contract> findByInitialDateBetween(LocalDate startDate, LocalDate endDate);

	public List<Contract> findByBalanceGreaterThan(double balance);

	public List<Contract> findByInitialDateLessThanEqualAndFinalDateGreaterThanEqual(LocalDate initialDate, LocalDate finalDate);

	@Query(value = "SELECT new com.api.joao.contract.dto.ContractFindDTO(c.id, c.description) FROM Contract c")
	public List<ContractFindDTO> findIdAndDescription();

	@Query(value = "SELECT new com.api.joao.contract.dto.ContractFindBalanceDescriptionDTO(c.description, c.balance) FROM Contract c")
	public List<ContractFindBalanceDescriptionDTO> findValueDescription();
}
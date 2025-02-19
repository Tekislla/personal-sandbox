package com.api.joao.contract.feign;

import java.util.List;

import lombok.Data;

@Data
public class ReturnList {
	private List<ContractValueDTO> calculatedValues;

}

package com.api.joao.contract.dto;

import javax.validation.constraints.NotNull;

public class ContractUpdateDTO extends ContractSaveDTO {
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

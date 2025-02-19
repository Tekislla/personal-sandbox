package com.multiple.bases.endereco.database.repo;

import org.springframework.data.repository.CrudRepository;

import com.multiple.bases.endereco.database.entity.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}

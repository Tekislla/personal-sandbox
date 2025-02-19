package com.multiple.bases.usuario.database.repo;

import org.springframework.data.repository.CrudRepository;

import com.multiple.bases.usuario.database.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}

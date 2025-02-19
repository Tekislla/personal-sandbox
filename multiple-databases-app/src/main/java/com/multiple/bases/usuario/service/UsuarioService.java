package com.multiple.bases.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiple.bases.usuario.database.entity.Usuario;
import com.multiple.bases.usuario.database.repo.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public void saveUser(Usuario user) {
		usuarioRepository.save(user);
	}
	
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioRepository.findAll();
	}
	
}

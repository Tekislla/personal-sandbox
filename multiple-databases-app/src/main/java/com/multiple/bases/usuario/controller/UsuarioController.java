package com.multiple.bases.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiple.bases.usuario.database.entity.Usuario;
import com.multiple.bases.usuario.service.UsuarioService;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {
	@Autowired
	UsuarioService service;
	
	@PostMapping(value = "/save")
	public void saveUser(@RequestBody Usuario user) {
		service.saveUser(user);
	}
	
	@GetMapping(value = "/find")
	public List<Usuario> findAll() {
		return service.findAll();
	}

}

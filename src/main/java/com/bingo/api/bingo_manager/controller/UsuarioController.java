package com.bingo.api.bingo_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
		return ResponseEntity.ok(usuarioService.create(usuario));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findbyId(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.findById(id));
	}
}

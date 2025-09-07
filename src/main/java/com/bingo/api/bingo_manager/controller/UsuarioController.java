package com.bingo.api.bingo_manager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bingo.api.bingo_manager.dto.UsuarioDTO;
import com.bingo.api.bingo_manager.dto.input.UsuarioInput;
import com.bingo.api.bingo_manager.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findbyId(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.findById(id));
	}
}

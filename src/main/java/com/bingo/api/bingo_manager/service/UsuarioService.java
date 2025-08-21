package com.bingo.api.bingo_manager.service;


import org.springframework.stereotype.Service;

import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario create(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado"));
	}
}

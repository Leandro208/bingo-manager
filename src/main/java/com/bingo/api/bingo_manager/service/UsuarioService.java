package com.bingo.api.bingo_manager.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.dto.UsuarioDTO;
import com.bingo.api.bingo_manager.dto.input.UsuarioInput;
import com.bingo.api.bingo_manager.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final ModelMapper modelMapper;

	public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
		this.usuarioRepository = usuarioRepository;
		this.modelMapper = modelMapper;
	}
	
	public UsuarioDTO create(UsuarioInput usuarioInput) {
		Usuario usuario = modelMapper.map(usuarioInput, Usuario.class);
		usuario = usuarioRepository.save(usuario);
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
	
	public UsuarioDTO findById(Long id) {
		return usuarioRepository.findById(id)
				.map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
				.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado"));
	}
}

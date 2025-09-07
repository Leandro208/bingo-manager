package com.bingo.api.bingo_manager.service;


import com.bingo.api.bingo_manager.domain.Role;
import com.bingo.api.bingo_manager.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.dto.UsuarioDTO;
import com.bingo.api.bingo_manager.dto.input.UsuarioInput;
import com.bingo.api.bingo_manager.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

	@Transactional
	public UsuarioDTO create(UsuarioInput usuarioInput) {
		Role basicRole = roleRepository.findByNome(Role.Values.BASIC.name());
		if(usuarioRepository.findByEmail(usuarioInput.getEmail()).isPresent()){
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já Existe um usuário com esse email");
		}
		Usuario usuario = modelMapper.map(usuarioInput, Usuario.class);
		usuario.setSenha(passwordEncoder.encode(usuarioInput.getSenha()));
		usuario.setRoles(Set.of(basicRole));
		return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
	}
	
	public UsuarioDTO findById(Long id) {
		return usuarioRepository.findById(id)
				.map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
				.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado"));
	}

	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}

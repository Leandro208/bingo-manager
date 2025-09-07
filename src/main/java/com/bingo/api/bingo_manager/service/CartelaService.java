package com.bingo.api.bingo_manager.service;

import com.bingo.api.bingo_manager.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.bingo.api.bingo_manager.domain.Cartela;
import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.dto.CartelaDTO;
import com.bingo.api.bingo_manager.dto.JogadorSimplesDTO;
import com.bingo.api.bingo_manager.dto.PartidaSimplesDTO;
import com.bingo.api.bingo_manager.repository.CartelaNumeroRepository;
import com.bingo.api.bingo_manager.repository.CartelaRepository;
import com.bingo.api.bingo_manager.repository.PartidaRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartelaService {

	private final CartelaRepository cartelaRepository;
	private final PartidaRepository partidaRepository;
	private final UsuarioRepository usuarioRepository;
	private final CartelaNumeroRepository cartelaNumeroRepository;

	private final ModelMapper modelMapper;
	
	public CartelaService(CartelaRepository cartelaRepository, PartidaRepository partidaRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper, CartelaNumeroRepository cartelaNumeroRepository) {
		this.cartelaRepository = cartelaRepository;
		this.partidaRepository = partidaRepository;
        this.usuarioRepository = usuarioRepository;
        this.cartelaNumeroRepository = cartelaNumeroRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional(readOnly = true)
	public CartelaDTO buscarCartelaUsuario(Long idPartida, JwtAuthenticationToken token) {
		if (!partidaRepository.existsById(idPartida)) {
			throw new EntityNotFoundException("Partida com ID " + idPartida + " não encontrada.");
		}
		Long idUsuarioLogado = Long.parseLong(token.getName());
		Cartela cartela = cartelaRepository.findByPartidaIdAndUserIdWithDetails(idPartida, idUsuarioLogado)
				.orElseThrow(() -> new EntityNotFoundException("Você não possui uma cartela nesta partida."));
		return modelMapper.map(cartela, CartelaDTO.class);
	}

}

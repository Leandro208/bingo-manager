package com.bingo.api.bingo_manager.service;

import org.modelmapper.ModelMapper;
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

@Service
public class CartelaService {

	private final CartelaRepository cartelaRepository;
	private final PartidaRepository partidaRepository;
	private final CartelaNumeroRepository cartelaNumeroRepository;

	private final ModelMapper modelMapper;
	
	public CartelaService(CartelaRepository cartelaRepository, PartidaRepository partidaRepository, ModelMapper modelMapper, CartelaNumeroRepository cartelaNumeroRepository) {
		this.cartelaRepository = cartelaRepository;
		this.partidaRepository = partidaRepository;
		this.cartelaNumeroRepository = cartelaNumeroRepository;
		this.modelMapper = modelMapper;
	}

	public CartelaDTO buscarCartelaUsuario(Long idPartida) {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Leandro Barbosa");

		Partida partida = partidaRepository.findById(idPartida)
				.orElseThrow(() -> new EntityNotFoundException("Partida não encontrada"));
		Cartela cartela = cartelaRepository.findByPartidaAndUsuario(partida, usuario);
		CartelaDTO cartelaDTO = new CartelaDTO();
		cartelaDTO.setId(cartela.getId());
		cartelaDTO.setPartida(modelMapper.map(partida, PartidaSimplesDTO.class));
		cartelaDTO.setUsuario(modelMapper.map(usuario, JogadorSimplesDTO.class));
		cartelaDTO.setNumeros(cartelaNumeroRepository.findAllByCartelaId(cartela.getId()));
		return cartelaDTO;
	}

}

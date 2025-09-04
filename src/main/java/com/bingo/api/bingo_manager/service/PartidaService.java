package com.bingo.api.bingo_manager.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bingo.api.bingo_manager.domain.Cartela;
import com.bingo.api.bingo_manager.domain.CartelaNumero;
import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.dto.PartidaDTO;
import com.bingo.api.bingo_manager.dto.PartidaDetalhesDTO;
import com.bingo.api.bingo_manager.dto.input.PartidaInput;
import com.bingo.api.bingo_manager.repository.CartelaNumeroRepository;
import com.bingo.api.bingo_manager.repository.CartelaRepository;
import com.bingo.api.bingo_manager.repository.PartidaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PartidaService {

	private final PartidaRepository partidaRepository;
	private final CartelaRepository cartelaRepository;
	private final CartelaNumeroRepository cartelaNumeroRepository;

	private final ModelMapper modelMapper;
	private final Long CRIADOR_FAKE = 1L;
	private final int QTD_NUMEROS_CARTELA = 10;

	public PartidaService(PartidaRepository partidaRepository, ModelMapper modelMapper,
			CartelaRepository cartelaRepository, CartelaNumeroRepository cartelaNumeroRepository) {
		this.partidaRepository = partidaRepository;
		this.cartelaRepository = cartelaRepository;
		this.cartelaNumeroRepository = cartelaNumeroRepository;
		this.modelMapper = modelMapper;
	}

	public PartidaDTO create(PartidaInput partidaInput) {
		Partida partida = modelMapper.map(partidaInput, Partida.class);
		partida.setCriador(new Usuario());
		partida.getCriador().setId(CRIADOR_FAKE);
		partida = partidaRepository.save(partida);
		return modelMapper.map(partida, PartidaDTO.class);
	}

	public Partida findById(Long partidaId) {
		return partidaRepository.findById(partidaId)
				.orElseThrow(() -> new EntityNotFoundException("Nenhuma partida encontrada"));

	}

	@Transactional(readOnly = true)
	public List<PartidaDetalhesDTO> findAll() {
		return partidaRepository.findAll().stream().map(partida -> {
			partida.getCartelas().iterator();
			partida.getNumerosSorteados().iterator();
			partida.getVencedores().iterator();
			return modelMapper.map(partida, PartidaDetalhesDTO.class);
		}).toList();
	}

	@Transactional
	public PartidaDetalhesDTO entrar(Long partidaId) {
		if (!partidaRepository.existsById(partidaId)) {
			throw new EntityNotFoundException("Partida não encontrada");
		}

		// cria cartela
		Cartela cartela = new Cartela();
		Partida partida = partidaRepository.findById(partidaId).get();
		cartela.setPartida(partida);
		cartela.setUsuario(new Usuario());
		cartela.getUsuario().setId(CRIADOR_FAKE);
		cartelaRepository.save(cartela);

		// cria numero
		List<CartelaNumero> listaNumeros = new ArrayList<>();
		List<Integer> numeros = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			numeros.add(i);
		}
		Collections.shuffle(numeros);
		List<Integer> numerosGerados = numeros.subList(0, QTD_NUMEROS_CARTELA);
		for (int i = 0; i < QTD_NUMEROS_CARTELA; i++) {
			CartelaNumero cartelaNumero = new CartelaNumero();
			cartelaNumero.setCartela(cartela);
			cartelaNumero.setMarcado(false);
			cartelaNumero.setNumero(numerosGerados.get(i));
			listaNumeros.add(cartelaNumero);
		}
		cartelaNumeroRepository.saveAll(listaNumeros);
		partida = partidaRepository.findById(partidaId).get();
		partida.getCartelas().iterator();
		partida.getNumerosSorteados().iterator();
		partida.getVencedores().iterator();
		return modelMapper.map(partida, PartidaDetalhesDTO.class);
	}
}

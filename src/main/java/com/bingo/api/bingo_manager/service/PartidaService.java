package com.bingo.api.bingo_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.repository.PartidaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PartidaService {

	private final PartidaRepository partidaRepository;
	
	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}
	
	public Partida create(Partida partida) {
		return partidaRepository.save(partida);
	}
	
	public Partida findById(Long id) {
		return partidaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhuma partida encontrada"));

	}
	
	public List<Partida> findAll(){
		return partidaRepository.findAll();
	}
}

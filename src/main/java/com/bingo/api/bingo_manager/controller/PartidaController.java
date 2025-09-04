package com.bingo.api.bingo_manager.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bingo.api.bingo_manager.dto.PartidaDTO;
import com.bingo.api.bingo_manager.dto.PartidaDetalhesDTO;
import com.bingo.api.bingo_manager.dto.input.PartidaInput;
import com.bingo.api.bingo_manager.service.PartidaService;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

	private final PartidaService partidaService;

	public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	}
	
	@PostMapping
	public ResponseEntity<PartidaDTO> create(@RequestBody PartidaInput partida, UriComponentsBuilder uriBuilder) {
		PartidaDTO partidaCriada = partidaService.create(partida);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(partidaCriada.getId()).toUri();

		return ResponseEntity.created(uri).body(partidaCriada);
	}
	
	@GetMapping
	public List<PartidaDetalhesDTO> findAll(){
		return partidaService.findAll();
	}
	
	@PostMapping("/{partidaId}/entrar")
	public ResponseEntity<PartidaDetalhesDTO> entrar(@PathVariable Long partidaId) {
		return ResponseEntity.ok(partidaService.entrar(partidaId));
	}
}

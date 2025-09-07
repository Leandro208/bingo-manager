package com.bingo.api.bingo_manager.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
	public ResponseEntity<PartidaDTO> create(@RequestBody PartidaInput partida, UriComponentsBuilder uriBuilder, JwtAuthenticationToken token) {
		PartidaDTO partidaCriada = partidaService.create(partida, token);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(partidaCriada.getId()).toUri();

		return ResponseEntity.created(uri).body(partidaCriada);
	}
	
	@GetMapping
	public List<PartidaDetalhesDTO> findAllDisponiveis() {
		return partidaService.findAllPartidasDisponiveis();
	}
	
	@PostMapping("/{partidaId}/entrar")
	public ResponseEntity<PartidaDetalhesDTO> entrar(@PathVariable Long partidaId,  JwtAuthenticationToken token) {
		return ResponseEntity.ok(partidaService.entrar(partidaId, token));
	}
	
	@PostMapping("/{partidaId}/iniciar")
	public ResponseEntity<PartidaDTO> iniciar(@PathVariable Long partidaId) {
		return ResponseEntity.ok(partidaService.iniciar(partidaId));
	}
	
	@GetMapping("/{partidaId}")
	public ResponseEntity<PartidaDetalhesDTO> buscarPorId(@PathVariable Long partidaId){
		return ResponseEntity.ok(partidaService.findPartidaDetalhesById(partidaId));
	}

    @PostMapping("/{partidaId}/encerrar")
    public ResponseEntity<PartidaDTO> encerrarPartida(@PathVariable Long partidaId) {
        return ResponseEntity.ok(partidaService.encerrarPartida(partidaId));
    }

}

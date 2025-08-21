package com.bingo.api.bingo_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.service.PartidaService;

@RestController
@RequestMapping("/partida")
public class PartidaController {

	private final PartidaService partidaService;

	public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	}
	
	@PostMapping
	public ResponseEntity<Partida> create(@RequestBody Partida partida){
		return ResponseEntity.ok(partidaService.create(partida));
	}
	
	@GetMapping
	public List<Partida> findAll(){
		return partidaService.findAll();
	}
	
	
}

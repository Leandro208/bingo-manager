package com.bingo.api.bingo_manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bingo.api.bingo_manager.dto.CartelaDTO;
import com.bingo.api.bingo_manager.service.CartelaService;

@RestController
@RequestMapping("/cartelas")
public class CartelaController {

	private final CartelaService cartelaService;
	
	public CartelaController(CartelaService cartelaService) {
		this.cartelaService = cartelaService;
	}

	@GetMapping("/{idPartida}/minha-cartela")
	public ResponseEntity<CartelaDTO> verCartela(@PathVariable Long idPartida){
		return ResponseEntity.ok(cartelaService.buscarCartelaUsuario(idPartida));
	}
}

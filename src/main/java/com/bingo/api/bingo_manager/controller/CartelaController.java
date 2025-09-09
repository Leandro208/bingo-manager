package com.bingo.api.bingo_manager.controller;

import com.bingo.api.bingo_manager.dto.input.DeleteCartelaInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bingo.api.bingo_manager.dto.CartelaDTO;
import com.bingo.api.bingo_manager.service.CartelaService;

import java.util.List;

@RestController
@RequestMapping("/cartelas")
public class CartelaController {

	private final CartelaService cartelaService;
	
	public CartelaController(CartelaService cartelaService) {
		this.cartelaService = cartelaService;
	}

	@GetMapping("/{idPartida}/minhas-cartelas")
	public ResponseEntity<List<CartelaDTO>> verMinhasCartelas(@PathVariable Long idPartida, JwtAuthenticationToken token){
		return ResponseEntity.ok(cartelaService.buscarCartelasDoUsuario(idPartida, token));
	}

    @PostMapping("/{idPartida}")
    public ResponseEntity<CartelaDTO> criarNovaCartela(JwtAuthenticationToken token, @PathVariable Long idPartida){
       return ResponseEntity.ok(cartelaService.criaCartela(idPartida, token));
    }

    @DeleteMapping("/{idPartida}")
    public void deletarCartela(JwtAuthenticationToken token, @PathVariable Long idPartida, @RequestBody DeleteCartelaInput deleteCartelaInput){
         cartelaService.apagarCartela(deleteCartelaInput.idCartela(), idPartida ,token);
    }
}

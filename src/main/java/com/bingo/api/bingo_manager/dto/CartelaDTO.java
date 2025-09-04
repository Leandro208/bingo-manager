package com.bingo.api.bingo_manager.dto;

import java.util.List;

public class CartelaDTO {

	private Long id;
	private PartidaSimplesDTO partida;
	private JogadorSimplesDTO usuario;
	
	private List<CartelaNumeroDTO> numeros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartelaNumeroDTO> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<CartelaNumeroDTO> numeros) {
		this.numeros = numeros;
	}

	public PartidaSimplesDTO getPartida() {
		return partida;
	}

	public void setPartida(PartidaSimplesDTO partida) {
		this.partida = partida;
	}

	public JogadorSimplesDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(JogadorSimplesDTO usuario) {
		this.usuario = usuario;
	}
	
}

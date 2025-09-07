package com.bingo.api.bingo_manager.dto.input;

import java.time.LocalDateTime;

import com.bingo.api.bingo_manager.domain.StatusPartida;

public class PartidaInput {
	
	private String nomePartida;
	private LocalDateTime dataPartida;
	
	public String getNomePartida() {
		return nomePartida;
	}
	public void setNomePartida(String nomePartida) {
		this.nomePartida = nomePartida;
	}
	public LocalDateTime getDataPartida() {
		return dataPartida != null ? dataPartida : LocalDateTime.now();
	}
	public void setDataPartida(LocalDateTime dataPartida) {
		this.dataPartida = dataPartida;
	}
	
	

}

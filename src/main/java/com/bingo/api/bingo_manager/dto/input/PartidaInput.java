package com.bingo.api.bingo_manager.dto.input;

import java.time.LocalDateTime;

import com.bingo.api.bingo_manager.domain.StatusPartida;

public class PartidaInput {
	
	private String nomePartida;
	private LocalDateTime dataPartida;
	private StatusPartida statusPartida;
	
	public String getNomePartida() {
		return nomePartida;
	}
	public void setNomePartida(String nomePartida) {
		this.nomePartida = nomePartida;
	}
	public LocalDateTime getDataPartida() {
		return dataPartida;
	}
	public void setDataPartida(LocalDateTime dataPartida) {
		this.dataPartida = dataPartida;
	}
	public StatusPartida getStatusPartida() {
		return statusPartida;
	}
	public void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}
	
	

}

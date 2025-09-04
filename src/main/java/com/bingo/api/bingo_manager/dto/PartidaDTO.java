package com.bingo.api.bingo_manager.dto;

import java.time.LocalDateTime;

import com.bingo.api.bingo_manager.domain.StatusPartida;
import com.bingo.api.bingo_manager.domain.Usuario;

public class PartidaDTO {
	
	private Long id;
	private String nomePartida;
	private LocalDateTime dataPartida;
	private LocalDateTime dataCadastro;
	private StatusPartida statusPartida;
	private Usuario criador;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public StatusPartida getStatusPartida() {
		return statusPartida;
	}
	public void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}
	public Usuario getCriador() {
		return criador;
	}
	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	
}

package com.bingo.api.bingo_manager.dto;

import java.util.List;

import com.bingo.api.bingo_manager.domain.StatusPartida;

public class PartidaDetalhesDTO {
	
	private Long id;
	private String nomePartida;
	private String dataPartida;
	private StatusPartida statusPartida;
	private JogadorSimplesDTO criador;
	private List<JogadorSimplesDTO> jogadores;
	private List<NumeroSorteadoDTO> numerosSorteados;
	
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
	public String getDataPartida() {
		return dataPartida;
	}
	public void setDataPartida(String dataPartida) {
		this.dataPartida = dataPartida;
	}
	public StatusPartida getStatusPartida() {
		return statusPartida;
	}
	public void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}
	public JogadorSimplesDTO getCriador() {
		return criador;
	}
	public void setCriador(JogadorSimplesDTO criador) {
		this.criador = criador;
	}
	public List<JogadorSimplesDTO> getJogadores() {
		return jogadores;
	}
	public void setJogadores(List<JogadorSimplesDTO> jogadores) {
		this.jogadores = jogadores;
	}
	public List<NumeroSorteadoDTO> getNumerosSorteados() {
		return numerosSorteados;
	}
	public void setNumerosSorteados(List<NumeroSorteadoDTO> numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}
	
	
	
}

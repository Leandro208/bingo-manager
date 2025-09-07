package com.bingo.api.bingo_manager.dto;

public class CartelaNumeroDTO {

	private int numero;
	private boolean marcado;

	public CartelaNumeroDTO() {
	}

	public CartelaNumeroDTO(int numero, boolean marcado) {
		this.numero = numero;
		this.marcado = marcado;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public boolean isMarcado() {
		return marcado;
	}
	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	
}

package com.bingo.api.bingo_manager.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "numero_sorteado")
public class NumeroSorteado implements PersistEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_numero_sorteado")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_partida")
	private Partida partida;

	private Integer numero;

	@Column(name = "ordem_sorteio")
	private Integer ordemSorteio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getOrdemSorteio() {
		return ordemSorteio;
	}

	public void setOrdemSorteio(Integer ordemSorteio) {
		this.ordemSorteio = ordemSorteio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumeroSorteado other = (NumeroSorteado) obj;
		return Objects.equals(id, other.id);
	}

	
	
}

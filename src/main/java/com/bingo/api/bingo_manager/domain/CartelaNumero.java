package com.bingo.api.bingo_manager.domain;

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
@Table(name = "cartela_numero")
public class CartelaNumero implements PersistEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cartela_numero")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cartela")
    private Cartela cartela;

    private Integer numero;

    private Boolean marcado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cartela getCartela() {
		return cartela;
	}

	public void setCartela(Cartela cartela) {
		this.cartela = cartela;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
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
		CartelaNumero other = (CartelaNumero) obj;
		return Objects.equals(id, other.id);
	}
    
}

package com.bingo.api.bingo_manager.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Cartela implements PersistEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cartela")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_partida")
    private Partida partida;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;

	@OneToMany(mappedBy = "cartela")
	private List<CartelaNumero> numeros;

    @Column(name = "numeros_hash")
    private int hashCodeNumeros;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<CartelaNumero> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<CartelaNumero> numeros) {
		this.numeros = numeros;
	}

    public int getHashCodeNumeros() {
        return hashCodeNumeros;
    }

    public void setHashCodeNumeros(int hashCodeNumeros) {
        this.hashCodeNumeros = hashCodeNumeros;
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
		Cartela other = (Cartela) obj;
		return Objects.equals(id, other.id);
	}
}

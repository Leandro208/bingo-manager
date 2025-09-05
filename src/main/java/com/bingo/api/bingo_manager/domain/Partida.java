package com.bingo.api.bingo_manager.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Partida implements PersistEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partida")
	private Long id;
	
	@Column(name = "nome_partida")
	private String nomePartida;
	
	@Column(name = "data_partida")
	private LocalDateTime dataPartida;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_partida")
	private StatusPartida statusPartida;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_criador")
	private Usuario criador;
	
    @OneToMany(mappedBy = "partida")
    private List<Cartela> cartelas;

    @OneToMany(mappedBy = "partida")
    private List<NumeroSorteado> numerosSorteados;

    @OneToMany(mappedBy = "partida")
    private List<Vencedor> vencedores;

    public boolean isAguardando() {
    	return statusPartida == StatusPartida.AGUARDANDO;
    }
    
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

	public StatusPartida getStatusPartida() {
		return statusPartida;
	}

	public void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public List<Cartela> getCartelas() {
		return cartelas;
	}

	public void setCartelas(List<Cartela> cartelas) {
		this.cartelas = cartelas;
	}

	public List<NumeroSorteado> getNumerosSorteados() {
		return numerosSorteados;
	}

	public void setNumerosSorteados(List<NumeroSorteado> numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}

	public List<Vencedor> getVencedores() {
		return vencedores;
	}

	public void setVencedores(List<Vencedor> vencedores) {
		this.vencedores = vencedores;
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
		Partida other = (Partida) obj;
		return Objects.equals(id, other.id);
	}
	
}

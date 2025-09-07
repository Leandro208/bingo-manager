package com.bingo.api.bingo_manager.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import com.bingo.api.bingo_manager.dto.input.LoginInput;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table
public class Usuario implements PersistEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;
	
	private String senha;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_role")
	)
	private Set<Role> roles;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isLoginCorrect(LoginInput loginInput, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(loginInput.senha(),this.senha);
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}

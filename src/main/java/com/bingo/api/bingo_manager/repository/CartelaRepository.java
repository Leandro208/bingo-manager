package com.bingo.api.bingo_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bingo.api.bingo_manager.domain.Cartela;
import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.domain.Usuario;

@Repository
public interface CartelaRepository extends JpaRepository<Cartela, Long>{

	Cartela findByPartidaAndUsuario(Partida partida, Usuario usuario);
}

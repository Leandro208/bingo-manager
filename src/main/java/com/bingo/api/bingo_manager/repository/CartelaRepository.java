package com.bingo.api.bingo_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bingo.api.bingo_manager.domain.Cartela;
import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.domain.Usuario;

import java.util.Optional;

@Repository
public interface CartelaRepository extends JpaRepository<Cartela, Long>{

	Optional<Cartela> findByPartidaAndUsuario(Partida partida, Usuario usuario);

	@Query("SELECT c FROM Cartela c " +
			"LEFT JOIN FETCH c.partida p " +
			"LEFT JOIN FETCH c.usuario u " +
			"LEFT JOIN FETCH c.numeros n " +
			"WHERE p.id = :partidaId AND u.id = :userId")
	Optional<Cartela> findByPartidaIdAndUserIdWithDetails(@Param("partidaId") Long partidaId, @Param("userId") Long userId
	);
}

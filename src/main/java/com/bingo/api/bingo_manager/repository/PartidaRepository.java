package com.bingo.api.bingo_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bingo.api.bingo_manager.domain.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long>{

}

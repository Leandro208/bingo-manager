package com.bingo.api.bingo_manager.repository;

import com.bingo.api.bingo_manager.domain.StatusPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bingo.api.bingo_manager.domain.Partida;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long>{
    List<Partida> findAllByStatusPartida(StatusPartida statusPartida);

    @Query("select p.statusPartida from Partida p " +
            "where p.id = :id")
    StatusPartida findStatusPartidaById(@Param("id") Long id);
}

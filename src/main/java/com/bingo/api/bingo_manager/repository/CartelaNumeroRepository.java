package com.bingo.api.bingo_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bingo.api.bingo_manager.domain.CartelaNumero;
import com.bingo.api.bingo_manager.dto.CartelaNumeroDTO;

@Repository
public interface CartelaNumeroRepository extends JpaRepository<CartelaNumero, Long> {

	@Query("SELECT new com.bingo.api.bingo_manager.dto.CartelaNumeroDTO(cn.numero, cn.marcado) "
			+ "FROM CartelaNumero cn WHERE cn.cartela.id = :cartelaId "
			+ "ORDER BY cn.numero")
	List<CartelaNumeroDTO> findAllByCartelaId(@Param("cartelaId") Long cartelaId);
}

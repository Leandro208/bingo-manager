package com.bingo.api.bingo_manager.repository;

import com.bingo.api.bingo_manager.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNome(String nome);
}

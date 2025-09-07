package com.bingo.api.bingo_manager.domain;

import jakarta.persistence.*;

@Entity
@Table
public class Role implements PersistEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;

    private String nome;

    @Override
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

    public enum Values{
        ADMIN(1L),
        BASIC(2L);

        long idRole;

        Values(long idRole){
            this.idRole = idRole;
        }

        public long getIdRole() {
            return idRole;
        }
    }
}

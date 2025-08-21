CREATE TABLE usuario (
    id_usuario BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    data_cadastro TIMESTAMP
);

CREATE TABLE partida (
    id BIGSERIAL PRIMARY KEY,
    nome_partida VARCHAR(255) NOT NULL,
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    status_partida VARCHAR(50) NOT NULL,
    data_cadastro TIMESTAMP,
    id_usuario_criador BIGINT NOT NULL,
    CONSTRAINT fk_partida_usuario FOREIGN KEY (id_usuario_criador) REFERENCES usuario (id_usuario)
);

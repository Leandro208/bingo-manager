CREATE TABLE usuario (
    id_usuario BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE partida (
    id_partida BIGSERIAL PRIMARY KEY,
    nome_partida VARCHAR(255),
    data_partida TIMESTAMP,
    status_partida VARCHAR(50),
    id_usuario_criador BIGINT NOT NULL,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_partida_usuario_criador
        FOREIGN KEY (id_usuario_criador) 
        REFERENCES usuario(id_usuario)
);

CREATE TABLE cartela (
    id_cartela BIGSERIAL PRIMARY KEY,
    id_partida BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cartela_partida
        FOREIGN KEY (id_partida) 
        REFERENCES partida(id_partida),
    
    CONSTRAINT fk_cartela_usuario
        FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario)
);

CREATE TABLE cartela_numero (
    id_cartela_numero BIGSERIAL PRIMARY KEY,
    id_cartela BIGINT NOT NULL,
    numero INTEGER,
    marcado BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_cartela_numero_cartela
        FOREIGN KEY (id_cartela) 
        REFERENCES cartela(id_cartela)
);

CREATE TABLE numero_sorteado (
    id_numero_sorteado BIGSERIAL PRIMARY KEY,
    id_partida BIGINT NOT NULL,
    numero INTEGER,
    ordem_sorteio INTEGER,

    CONSTRAINT fk_numero_sorteado_partida
        FOREIGN KEY (id_partida) 
        REFERENCES partida(id_partida)
);

CREATE TABLE vencedor (
    id BIGSERIAL PRIMARY KEY,
    id_partida BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,

    CONSTRAINT fk_vencedor_partida
        FOREIGN KEY (id_partida) 
        REFERENCES partida(id_partida),

    CONSTRAINT fk_vencedor_usuario
        FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario)
);

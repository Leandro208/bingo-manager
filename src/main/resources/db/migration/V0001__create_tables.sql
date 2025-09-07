-- =================================================================
-- TABELA DE USUÁRIOS
-- Armazena os dados dos jogadores.
-- =================================================================
CREATE TABLE usuario (
    id_usuario BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255),
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =================================================================
-- TABELA DE PERFIS (ROLES)
-- Armazena os tipos de perfis de usuário (ex: ADMIN, BASIC).
-- =================================================================
CREATE TABLE role (
    id_role BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- =================================================================
-- TABELA DE JUNÇÃO USUARIO-ROLE (user_roles)
-- Mapeia a relação Muitos-para-Muitos entre usuários e perfis.
-- =================================================================
CREATE TABLE user_roles (
    id_usuario BIGINT NOT NULL,
    id_role BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_role),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_role) REFERENCES role(id_role)
);

-- =================================================================
-- TABELA DE PARTIDAS
-- Armazena as informações de cada partida de bingo.
-- =================================================================
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

-- =================================================================
-- TABELA DE CARTELAS
-- Armazena as cartelas geradas para cada jogador em uma partida.
-- =================================================================
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

-- =================================================================
-- TABELA DE NÚMEROS DA CARTELA
-- Armazena cada número individual de uma cartela.
-- =================================================================
CREATE TABLE cartela_numero (
    id_cartela_numero BIGSERIAL PRIMARY KEY,
    id_cartela BIGINT NOT NULL,
    numero INTEGER,
    marcado BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_cartela_numero_cartela
        FOREIGN KEY (id_cartela)
        REFERENCES cartela(id_cartela)
);

-- =================================================================
-- TABELA DE NÚMEROS SORTEADOS
-- Registra os números sorteados em cada partida.
-- =================================================================
CREATE TABLE numero_sorteado (
    id_numero_sorteado BIGSERIAL PRIMARY KEY,
    id_partida BIGINT NOT NULL,
    numero INTEGER,
    ordem_sorteio INTEGER,

    CONSTRAINT fk_numero_sorteado_partida
        FOREIGN KEY (id_partida)
        REFERENCES partida(id_partida)
);

-- =================================================================
-- TABELA DE VENCEDORES
-- Registra o(s) vencedor(es) de cada partida.
-- =================================================================
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

-- =================================================================
-- DADOS INICIAIS (SEED)
-- Popula a tabela de perfis com valores padrão.
-- =================================================================
INSERT INTO role (nome) VALUES ('ADMIN'), ('BASIC');
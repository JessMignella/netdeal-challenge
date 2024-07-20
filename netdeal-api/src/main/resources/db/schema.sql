CREATE DATABASE IF NOT EXISTS funcionario_db;

USE funcionario_db;

CREATE TABLE IF NOT EXISTS cargo (
    titulo VARCHAR(255) NOT NULL,
    nivel INT NOT NULL,
    PRIMARY KEY (titulo, nivel)
);

CREATE TABLE IF NOT EXISTS forca_senha (
    score INT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    PRIMARY KEY (score)
);


CREATE TABLE IF NOT EXISTS funcionario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cargo_titulo VARCHAR(255) NOT NULL,
    cargo_nivel INT NOT NULL,
    lideranca_id BIGINT,
    senha VARCHAR(255) NOT NULL,
    forca_senha_score INT NOT NULL,
    FOREIGN KEY (cargo_titulo, cargo_nivel) REFERENCES cargo (titulo, nivel),
    FOREIGN KEY (forca_senha_score) REFERENCES forca_senha (score),
    FOREIGN KEY (lideranca_id) REFERENCES funcionario (id)
);


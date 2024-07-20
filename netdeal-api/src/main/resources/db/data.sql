-- Inserção de cargos
INSERT INTO cargo (titulo, nivel) VALUES ('Estagiario', 3);
INSERT INTO cargo (titulo, nivel) VALUES ('Assistente', 3);
INSERT INTO cargo (titulo, nivel) VALUES ('Gerente', 2);
INSERT INTO cargo (titulo, nivel) VALUES ('Analista', 2);
INSERT INTO cargo (titulo, nivel) VALUES ('Supervisor', 2);
INSERT INTO cargo (titulo, nivel) VALUES ('Coordenador', 2);
INSERT INTO cargo (titulo, nivel) VALUES ('Diretor', 1);
INSERT INTO forca_senha (score, descricao)
VALUES
  (0, 'Muito Fraca'),
  (10, 'Muito Fraca'),
  (16, 'Muito Fraca'),
  (18, 'Muito Fraca'),
  (19, 'Muito Fraca'),
  (20, 'Fraca'),
  (24, 'Fraca'),
  (26, 'Fraca'),
  (27, 'Fraca'),
  (30, 'Fraca'),
  (41, 'Media'),
  (42, 'Media'),
  (45, 'Media'),
  (46, 'Media'),
  (50, 'Media'),
  (53, 'Media'),
  (57, 'Media'),
  (59, 'Media'),
  (68, 'Forte'),
  (70, 'Forte'),
  (73, 'Forte'),
  (90, 'Muito Forte');

-- Inserção de funcionários
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Alice Silva', 'Analista', 2, 6, '$2a$10$F3xGFOTYnlLIzUt5sM3MZuTmPwysd2OIMDdt2H6Ku/n0jEnsWeD/O', 20);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Bruno Souza', 'Supervisor', 2, 1, 'encrypted_password2', 70);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Carla Nascimento', 'Gerente', 2, 2, 'encrypted_password3', 90);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Daniel Costa', 'Estagiario', 3, 3, 'encrypted_password4', 30);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Elaine Mendes', 'Analista', 2, 1, 'encrypted_password5', 70);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Fernando Lima', 'Coordenador', 2, 2, 'encrypted_password6', 50);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Gabriela Martins', 'Diretor', 1, 3, 'encrypted_password7', 90);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Henrique Alves', 'Supervisor', 2, 4, 'encrypted_password8', 70);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Isabela Pereira', 'Estagiario', 3, 5, 'encrypted_password9', 30);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Joo Fernandes', 'Coordenador', 2, 6, 'encrypted_password10', 50);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Maycon Douglas', 'Assistente', 3, 1, '$2a$10$8t8/0HvG5FCbfUvdhUvVNOsnVJh7nGRQPe54Rsunm0a512JT2uag2', 68);
INSERT INTO funcionario (nome, cargo_titulo, cargo_nivel, lideranca_id, senha, forca_senha_score) VALUES ('Lucas Eduardo', 'Assistente', 3, 1, '$2a$10$E4F1T8tw7rg7Sxc.lv2cXe/bNU8LRtIcvgTWnPK/3NYiriMCMH0G.', 68);
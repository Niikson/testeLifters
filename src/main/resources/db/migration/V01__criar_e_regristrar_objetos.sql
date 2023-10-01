CREATE TABLE candidato (
	id BINARY(36) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	numero INT UNIQUE,
	legenda VARCHAR(50),
	cargo VARCHAR(50) NOT NULL,
	criadoEm DATETIME,
	alteradoEm DATETIME,
	deletadoEm DATETIME,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO candidato (id, nome, numero, legenda, cargo, criadoEm) values (UUID(), 'Candidato A', 01, 'A', 'Presidente', CURRENT_TIMESTAMP);
INSERT INTO candidato (id, nome, numero, legenda, cargo, criadoEm) values (UUID(), 'Candidato B', 02, 'B', 'Presidente', CURRENT_TIMESTAMP);

CREATE TABLE cargo (
	id BINARY(36) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	criadoEm DATETIME,
	alteradoEm DATETIME,
	deletadoEm DATETIME,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO cargo (id, nome, criadoEm) values (UUID(), 'Analista', CURRENT_TIMESTAMP);
INSERT INTO cargo (id, nome, criadoEm) values (UUID(), 'Desenvolvedor', CURRENT_TIMESTAMP);

CREATE TABLE eleitor (
	id BINARY(36) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	cpf INT UNIQUE,
	idCargo BINARY(36) NOT NULL,
	criadoEm DATETIME,
	alteradoEm DATETIME,
	deletadoEm DATETIME,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO eleitor (id, nome, cpf, idCargo, criadoEm) values (UUID(), 'Eleitor A', 111, (SELECT id FROM lifters.cargo WHERE nome = 'Analista'), CURRENT_TIMESTAMP);
INSERT INTO eleitor (id, nome, cpf, idCargo, criadoEm) values (UUID(), 'Eleitor B', 222, (SELECT id FROM lifters.cargo WHERE nome = 'Desenvolvedor'), CURRENT_TIMESTAMP);
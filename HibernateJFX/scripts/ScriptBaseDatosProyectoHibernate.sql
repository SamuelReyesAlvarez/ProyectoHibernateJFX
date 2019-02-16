CREATE DATABASE IF NOT EXISTS valorator;
USE valorator;

CREATE TABLE IF NOT EXISTS empresa(
	idEmpresa INTEGER AUTO_INCREMENT NOT NULL,
	nombre VARCHAR (25) NOT NULL,
	CONSTRAINT pkIdEmpresa PRIMARY KEY (idEmpresa)
);

CREATE TABLE IF NOT EXISTS juego(
	idJuego INTEGER AUTO_INCREMENT NOT NULL,
	nombre VARCHAR (25) NOT NULL,
	estilo VARCHAR (15) NULL,
	publicacion DATE NOT NULL,
	descripcion VARCHAR (250) NULL,
	precio DECIMAL (6,2) NOT NULL,
	CONSTRAINT pkIdJuego PRIMARY KEY (idJuego)
);

CREATE TABLE IF NOT EXISTS desarrolla(
	idEmpresa INTEGER NOT NULL,
	idJuego INTEGER NOT NULL,
	CONSTRAINT pkIdEmpresaIdJuego PRIMARY KEY (idEmpresa, idJuego),
	CONSTRAINT fkIdEmpresaDesarrolla FOREIGN KEY (idEmpresa) REFERENCES empresa(idEmpresa),
	CONSTRAINT fkIdjuegoDesarrolla FOREIGN KEY (idJuego) REFERENCES juego(idJuego)
);

CREATE TABLE IF NOT EXISTS valoracion(
	idValoracion INTEGER AUTO_INCREMENT NOT NULL,
	idJuego INTEGER NOT NULL,
	voto TINYINT NOT NULL,
	comentario VARCHAR (250) NULL,
	CONSTRAINT pkIdValoracion PRIMARY KEY (idValoracion),
	CONSTRAINT fkIdjuegoValoracion FOREIGN KEY (idJuego) REFERENCES juego(idJuego)
);
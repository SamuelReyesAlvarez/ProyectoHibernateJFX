DROP DATABASE valorator;

CREATE DATABASE IF NOT EXISTS valorator;
USE valorator;

CREATE TABLE IF NOT EXISTS empresa(
	idEmpresa INTEGER AUTO_INCREMENT NOT NULL,
	nombre VARCHAR (25) NOT NULL UNIQUE,
	CONSTRAINT pkIdEmpresa PRIMARY KEY (idEmpresa)
);

CREATE TABLE IF NOT EXISTS juego(
	idJuego INTEGER AUTO_INCREMENT NOT NULL,
	nombre VARCHAR (25) NOT NULL UNIQUE,
	estilo VARCHAR (15) NULL,
	publicacion DATE NOT NULL,
	descripcion VARCHAR (250) NULL,
	precio DECIMAL (6,2) NOT NULL,
	CONSTRAINT pkIdJuego PRIMARY KEY (idJuego)
);

CREATE TABLE IF NOT EXISTS desarrolla(
	idDesarrolla INTEGER AUTO_INCREMENT NOT NULL,
	idEmpresa INTEGER NULL,
	idJuego INTEGER NULL,
	CONSTRAINT pkidDesarrolla PRIMARY KEY (idDesarrolla),
	CONSTRAINT fkIdEmpresaDesarrolla FOREIGN KEY (idEmpresa) REFERENCES empresa(idEmpresa),
	CONSTRAINT fkIdjuegoDesarrolla FOREIGN KEY (idJuego) REFERENCES juego(idJuego)
);

CREATE TABLE IF NOT EXISTS valoracion(
	idValoracion INTEGER AUTO_INCREMENT NOT NULL,
	idJuego INTEGER NULL,
	voto TINYINT NOT NULL,
	comentario VARCHAR (250) NOT NULL,
	CONSTRAINT pkIdValoracion PRIMARY KEY (idValoracion),
	CONSTRAINT fkIdjuegoValoracion FOREIGN KEY (idJuego) REFERENCES juego(idJuego)
);

INSERT INTO empresa(nombre) VALUES('Valve');
INSERT INTO empresa(nombre) VALUES('Ubisoft');
INSERT INTO empresa(nombre) VALUES('Blizzard');
INSERT INTO empresa(nombre) VALUES('iPlayGames');

INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('Half Life', 'aventura', '1998/11/19', 'Half-Life es un videojuego del género ciencia ficción y disparos en primera persona desarrollado por Valve Corporation. Supuso este juego el debut de la compañía y el primero de lo que posteriormente pasaría a ser la serie Half-Life.', 9.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('Dota 2', 'rol', '2013/07/09', 'Dota 2 es un videojuego perteneciente al género ARTS, también conocido como MOBA, lanzado el 9 de julio de 2013. El juego fue desarrollado por la empresa Valve Corporation.', 19.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('Portal', null, '2007/10/09', 'Portal es un videojuego de lógica en primera persona para un solo jugador desarrollado por Valve Corporation. El juego se publicó en un paquete llamado The Orange Box', 12.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('Asassins Creed', 'aventura', '2007/11/16', 'Assassins Creed es una serie de videojuegos, historietas, libros, y cortos de ficción histórica. Los videojuegos son de acción-aventura, sigilo y de mundo abierto.', 45.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('The Crew', null, '2014/12/02', 'The Crew es un videojuego de conducción multijugador situado en un entorno de mundo abierto basado en los Estados Unidos, desarrollado por Ivory Tower y distribuido por Ubisoft.', 24.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('For Honor', 'accion', '2017/02/14', 'For Honor es un videojuego de acción en tercera persona que destaca por su sistema de combate tridireccional. Ha sido desarrollado por Ubisoft Montreal y distribuido por Ubisoft para las plataformas Microsoft Windows, PlayStation 4 y Xbox One.', 49.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('Diablo III', 'rol', '2012/05/15', 'Diablo III es un videojuego de rol de acción, desarrollado por Blizzard Entertainment. Ésta es la continuación de Diablo II y la tercera parte de la serie que fue creada por la compañía estadounidense Blizzard.', 29.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('World of Warcraft', 'estrategia', '2004/11/23', 'World of Warcraft es un videojuego de rol multijugador masivo en línea desarrollado por Blizzard Entertainment. Es el cuarto juego lanzado establecido en el universo fantástico de Warcraft.', 49.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('Overwatch', null, '2016/05/24', 'Overwatch es un videojuego de disparos en primera persona multijugador, desarrollado por Blizzard Entertainment. Fue lanzado al público el 24 de mayo del 2016, para las plataformas PlayStation 4, Xbox One y Microsoft Windows.', 29.95);
INSERT INTO juego(nombre, estilo, publicacion, descripcion, precio) VALUES('Spacial Conqueror', 'estrategia', '2018/12/19', 'Conquistador Espacial (Spacial Conqueror en inglés) es un juego de estrategia en tiempo real en el que los jugadores luchan por dominar el universo', 19.95);

INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(1, 1);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(1, 2);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(1, 3);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(2, 4);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(2, 5);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(2, 6);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(3, 7);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(3, 8);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(3, 9);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(4, 10);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(1, 10);
INSERT INTO desarrolla(idEmpresa, idJuego) VALUES(4, 8);

INSERT INTO valoracion(idJuego, voto, comentario) VALUES(10, true, 'Es el mejor juego que he visto nunca');
INSERT INTO valoracion(idJuego, voto, comentario) VALUES(10, false, 'Tiene muchos bugs');
INSERT INTO valoracion(idJuego, voto, comentario) VALUES(10, true, 'Tiene algunos bugs pero es jugable');
INSERT INTO valoracion(idJuego, voto, comentario) VALUES(10, true, 'Se nota la entrega que le ponen en el desarrollo');
INSERT INTO valoracion(idJuego, voto, comentario) VALUES(10, false, 'Aun esta muy verde');
INSERT INTO valoracion(idJuego, voto, comentario) VALUES(10, true, 'Es gratis!! Que mas quieres?');

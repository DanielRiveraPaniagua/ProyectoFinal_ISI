.open sample.db

CREATE TABLE Directores(

	id_director INTEGER NOT NULL,
	nombre TEXT NOT NULL,
	apellido TEXT NOT NULL,
	fecha_nacimiento TEXT NOT NULL,
	fecha_muerte TEXT NOT NULL,
	pais_origen TEXT NOT NULL,
	PRIMARY KEY(id_director)
);

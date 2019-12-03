.open sample.db

CREATE TABLE pertenece(
	nombre_genero VARCHAR(50),
	id_pelicula INT,
	PRIMARY KEY(nombre_genero, id_pelicula),
	FOREING KEY(nombre_genero) REFERENCES Generos(id_genero),
	FOREING KEY(id_pelicula) REFERENCES Calificacion(id_pelicula) ON DELETE CASCADE
	


);

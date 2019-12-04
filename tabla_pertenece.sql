.open sample.db

CREATE TABLE pertenece(
	id_genero INTEGER NOT NULL,
	id_pelicula INT,
	PRIMARY KEY(id_genero, id_pelicula),
	FOREING KEY(id_genero) REFERENCES Generos(id_genero),
	FOREING KEY(id_pelicula) REFERENCES Peliculas(id_pelicula) ON DELETE CASCADE

);




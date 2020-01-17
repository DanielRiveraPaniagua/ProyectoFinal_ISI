.open sample.db

CREATE TABLE pertenece(
	id_genero INTEGER NOT NULL,
	id_film INT,
	PRIMARY KEY(id_genero, id_film),
	FOREING KEY(id_genero) REFERENCES Generos(id_film),
	FOREING KEY(id_film) REFERENCES Peliculas(id_film) ON DELETE CASCADE

);




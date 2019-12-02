.open sample.db

CREATE TABLE adecuado_para(
	id_edades INT,
	id_pelicula INT,
	PRIMARY KEY(id_edades, id_peliculas),
	FOREING KEY(id_edades REFERENCES Calificacion(id_edades),
	FOREING KEY(id_pelicula) REFERENCES Peliculas(id_pelicula) ON DELETE CASCADE
);

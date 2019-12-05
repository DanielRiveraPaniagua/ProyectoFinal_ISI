.open sample.db

CREATE TABLE escriben(
	id_pelicula INT NOT NULL,
	id_guionista INT NOT NULL,
	PRIMARY KEY (id_pelicula, id_guionista),
	FOREIGN KEY(id_pelicula) REFERENCES peliculas(id_pelicula),
	FOREIGN KEY(id_guionista) REFERENCES guionistas(id_guionista)
);


// INSERTS

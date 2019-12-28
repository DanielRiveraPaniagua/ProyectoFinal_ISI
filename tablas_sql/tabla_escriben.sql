.open sample.db

CREATE TABLE escriben(
	id_film INT NOT NULL,
	id_guionista INT NOT NULL,
	PRIMARY KEY (id_film, id_guionista),
	FOREIGN KEY(id_film) REFERENCES peliculas(id_film),
	FOREIGN KEY(id_guionista) REFERENCES guionistas(id_guionista)
);


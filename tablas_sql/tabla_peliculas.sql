.open sample.db

CREATE TABLE Peliculas(
	id_film INT NOT NULL,
	titulo TEXT NOT NULL,
	año INT,
	duracion TEXT,
	isAdult INT,
	rating INT,
	num_votos INT,
	PRIMARY KEY(id_film)
);


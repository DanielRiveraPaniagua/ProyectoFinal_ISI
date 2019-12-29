.open sample.db

CREATE TABLE Peliculas(
	id_film INT NOT NULL,
	titulo TEXT NOT NULL,
	año INT,
	duracion TEXT,
	isAdult INT,
	rating INT,
<<<<<<< HEAD
	PRIMARY KEY(id_pelicula)
=======
	num_votos INT,
	PRIMARY KEY(id_film)
>>>>>>> a647af1dc7ad49365a4dddefa47e720f45fdc118
);


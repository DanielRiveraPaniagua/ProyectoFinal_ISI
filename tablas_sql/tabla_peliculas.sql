--Creación la tabla Películas 
.open sample.db

CREATE TABLE Peliculas(
	id_pelicula INT NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	anyo INT,
	duracion VARCHAR(100),
	rating INT,
	num_votos INT,
	isadult INT,
	PRIMARY KEY(id_pelicula)
);


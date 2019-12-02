--Creación tabla Películas 
.open sample.db

CREATE TABLE Peliculas(
	id_pelicula VARCHAR(100) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	anyo INT,
	duracion VARCHAR(100),
	rating VARCHAR(100),
	PRIMARY KEY(id_pelicula)
);



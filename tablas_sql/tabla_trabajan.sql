.open sample.db

CREATE TABLE trabajan(id_pelicula INT NOT NULL,
		      id_actor INT NOT NULL,
		      PRIMARY KEY(id_pelicula, id_actor)
		      FOREIGN KEY id_pelicula REFERENCES peliculas(id_pelicula)
		      FOREIGN KEY id_actor REFERENCES actores(id_actor));

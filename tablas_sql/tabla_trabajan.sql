.open sample.db

CREATE TABLE trabajan(
	      id_film INT NOT NULL,
	      id_actor INT NOT NULL,
	      PRIMARY KEY(id_film, id_actor)
	      FOREIGN KEY id_film REFERENCES peliculas(id_film)
	      FOREIGN KEY id_actor REFERENCES actores(id_actor)
);

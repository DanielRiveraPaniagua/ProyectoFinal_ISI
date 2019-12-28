
.open sample.db

CREATE TABLE dirigen (id_pelicula INT NOT NULL, 
		id_director INT NOT NULL, 
		PRIMARY KEY (id_film, id_director),
		FOREIGN KEY id_film REFERENCES peliculas(id_film),
		FOREIGN KEY id_director REFERENCES directores(id_director)
);


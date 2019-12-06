
.open sample.db

CREATE TABLE dirigen (id_pelicula INT NOT NULL, 
		id_director INT NOT NULL, 
		PRIMARY KEY (id_pelicula, id_director),
		FOREIGN KEY id_pelicula REFERENCES peliculas(id_pelicula),
		FOREIGN KEY id_director REFERENCES directores(id_director));

INSERTS //

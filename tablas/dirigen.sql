
.open sample.db

CREATE TABLE dirigen (id_pel INT NOT NULL, 
		id_direc INT NOT NULL, 
		PRIMARY KEY (id_pel, id_direc),
		FOREIGN KEY id_pel REFERENCES peliculas(id_pel),
		FOREIGN KEY id_direc REFERENCES directores(id_direc));

INSERTS //

.open sample.db

CREATE TABLE Directores(
	id_director INTEGER NOT NULL,
	nombre_y_apellido TEXT NOT NULL,
	año_nacimiento TEXT NOT NULL,
	año_muerte TEXT NOT NULL,
	PRIMARY KEY(id_director)
);

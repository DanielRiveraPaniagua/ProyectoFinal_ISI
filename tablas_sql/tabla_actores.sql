.open sample.db

CREATE TABLE Actores (
	id_actor INTEGER NOT NULL,
	nombre_y_apellido TEXT NOT NULL,
	año_nacimiento TEXT NOT NULL,
	año_muerte TEXT NOT NULL,
	PRIMARY KEY(id_actor)
);

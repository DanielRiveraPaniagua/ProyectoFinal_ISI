.open sample.db

CREATE TABLE Guionistas(
	id_guionista INTEGER NOT NULL,
	nombre_y_apellido TEXT NOT NULL,
	año_nacimiento TEXT NOT NULL,
	año_muerte TEXT NOT NULL,
	PRIMARY KEY(id_guionista)
);

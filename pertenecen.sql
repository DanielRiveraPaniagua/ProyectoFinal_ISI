.open sample.db

CREATE TABLE pertenecen(
	id_genero INT,
	id_pelicula INT,
	PRIMARY KEY(id_genero, id_pelicula),
	FOREING KEY(id_genero) REFERENCES Generos(id_genero),
	FOREING KEY(id_pelicula) REFERNCES Calificacion(id_pelicula) ON DELETE CASCADE
	


);

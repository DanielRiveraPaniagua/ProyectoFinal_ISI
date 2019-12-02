.open sample.db

CREATE TABLE adecuado_para(
	id_clasificacion INT,
	id_pelicula INT,
	PRIMARY KEY(id_edades, id_peliculas),
	FOREING KEY(id_edades REFERENCES Edades(id_edades),
	FOREING KEY(id_pelicula) REFERNCES Peliculas(id_pelicula) ON DELETE CASCADE
	


);

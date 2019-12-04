.open sample.db

CREATE TABLE escriben(
	id_pel INT NOT NULL,
	id INT NOT NULL,
	PRIMARY KEY (id_pel, id),
	FOREIGN KEY(id_pel) REFERENCES peliculas(id_pel),
	FOREIGN KEY(id) REFERENCES guionistas(id)
);


// INSERTS

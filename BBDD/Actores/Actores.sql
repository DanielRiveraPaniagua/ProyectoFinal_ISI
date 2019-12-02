.open sample.db

CREATE TABLE Actores ( 
id_actor INT NOT NULL,
nombre VARCHAR(50) NOT NULL,
apellido VARCHAR(50) NOT NULL,
pais_de_origen VARCHAR(10) NOT NULL,
fecha_nacimiento VARCHAR(10) NOT NULL, 
fecha_muerte VARCHAR(10),    
PRIMARY KEY(id_actor));

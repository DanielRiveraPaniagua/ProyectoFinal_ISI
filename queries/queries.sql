.open sample.db



#Películas por región.

SELECT peliculas.nombre, peliculas.pais
FROM peliculas
GROUP BY peliculas.pais
ORDER BY peliculas.pais


#Actores que trabajaron con cierto director.

SELECT actores.nombre || ' ' || actores.apellido AS 'Nombre actor', 
directores.nombre || ' ' || directores.apellido AS 'Nombre director'
FROM actores
JOIN peliculas ON actores.id_pel = peliculas.id_pel
JOIN directores ON peliculas.id_pel = directores.id_pel
ORDER BY actores.apellido


#Peliculas por idioma.

SELECT peliculas.nombre, peliculas.idioma
FROM peliculas
GROUP BY peliculas.idioma
ORDER BY peliculas.idioma


#Directores que trabajaron con cierto director.

SELECT directores.nombre || ' ' || directores.apellido AS 'Nombre director', 
actores.nombre || ' ' || actores.apellido AS 'Nombre actor'
FROM directores
JOIN peliculas ON directores.id_pel = peliculas.id_pel
JOIN directores ON peliculas.id_pel = actores.id_pel
ORDER BY directores.apellido


#Películas escritas por cierto guionista.

SELECT peliculas.nombre,
guionistas.nombre || ' ' || guionistas.apellido AS 'Nombre guionista'
FROM guionistas
JOIN peliculas ON guionistas.id_pel = peliculas.id_pel
GROUP BY guionistas.id_pel
ORDER BY guionistas.apellido




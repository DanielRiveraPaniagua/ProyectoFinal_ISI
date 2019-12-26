.open sample.db


#Películas por género

SELECT peliculas.nombre, generos.nombre
FROM peliculas
JOIN pertenece
ON peliculas.id_pelicula = pertenece.id_pelicula
JOIN generos
ON pertenece.id_genero = generos.id_genero
GROUP BY generos.nombre
ORDER BY generos.nombre

#Peliculas para adultos

SELECT peliculas.nombre
FROM peliculas 
JOIN adecuado_para
ON peliculas.id_pelicula = adecuado_para.id_pelicula
JOIN calificacion
ON adecuado_para.id_edades = calificacion.id_edades
WHERE calificacion.is_adult = 1

#Películas para niños
SELECT peliculas.nombre
FROM peliculas 
JOIN adecuado_para
ON peliculas.id_pelicula = adecuado_para.id_pelicula
JOIN calificacion
ON adecuado_para.id_edades = calificacion.id_edades
WHERE calificacion.is_adult = 0


#Actores por película

SELECT actores.nombre || ' ' || actores.apellido, peliculas.nombre
FROM actores
JOIN trabajan
ON actores.id_actor = trabajan.id_actor
JOIN peliculas
ON trabajan.id_pel = peliculas.id_pel
ORDER BY peliculas.nombre

#Películas por actor

SELECT peliculas.nombre, actores.nombre || ' ' || actores.apellido
FROM peliculas
JOIN trabajan
ON peliculas.id_pel = trabajan.id_pel
JOIN actores
ON trabajan.id_actor = actores.id_actor
ORDER BY actores.apellido

#N Películas por valoración

SELECT peliculas.nombre, peliculas.rating
FROM peliculas
GROUP BY peliculas.rating 
ORDER BY peliculas.rating DESC
LIMIT 10

#Películas por año

SELECT peliculas.nombre, peliculas.anyo
FROM peliculas
GROUP BY peliculas.anyo
ORDER BY peliculas.anyo

#Películas por director

SELECT peliculas.nombre,
directores.nombre || ' ' || directores.apellidos
FROM peliculas
JOIN dirige
ON peliculas.id_pel = dirige.id_pel
JOIN directores
ON dirige.id_direc = directores.id_direc
ORDER BY directores.apellidos

#Películas por duración

SELECT peliculas.nombre,
peliculas.duracion
FROM peliculas
ORDER BY peliculas.duracion

#Información de las películas

SELECT peliculas.nombre,
peliculas.año,
peliculas.duracion,
peliculas.rating
FROM peliculas
ORDER BY peliculas.nombre

#Películas de un actor de cierto género

SELECT peliculas.nombre ,
actores.nombre || ' ' || actores.apellidos,
generos.nombre
FROM peliculas
JOIN trabajan
ON peliculas.id_pel = trabajan.id_pel
JOIN actores
ON  trabajan.id_actor = actores.id_actor
JOIN pertenecen
ON peliculas.id_pel = pertenecen.id_pel
JOIN generos
ON pertenecen.id_gen = generos.id_gen


#Películas por región.

SELECT peliculas.nombre, peliculas.pais
FROM peliculas
GROUP BY peliculas.pais
ORDER BY peliculas.pais


#Actores que trabajaron con cierto director.

SELECT actores.nombre || ' ' || actores.apellido AS 'Nombre actor',
directores.nombre || ' ' || directores.apellido AS 'Nombre director'
FROM actores
JOIN trabajan ON actores.id_actor = trabajan.id_actor
JOIN peliculas ON trabajan.id_pel = peliculas.id_pel
JOIN dirigen ON peliculas.id_pel = dirigen.id_pel
JOIN directores ON dirigen.id_direc = directores.id_direc
ORDER BY actores.apellido


#Peliculas por idioma.

SELECT peliculas.nombre, peliculas.idioma
FROM peliculas
GROUP BY peliculas.idioma
ORDER BY peliculas.idioma


#Directores que trabajaron con cierto actor.

SELECT directores.nombre || ' ' || directores.apellido AS 'Nombre director',
actores.nombre || ' ' || actores.apellido AS 'Nombre actor'
FROM directores
JOIN dirigen on directores. id_direc = dirigen.id_direc 
JOIN peliculas ON dirigen.id_pel = peliculas.id_pel
JOIN trabajan ON peliculas.id_pel = trabajan.id_pel
JOIN actores ON trabajan.id_actor = actores.id_actor
ORDER BY directores.apellido


#Películas escritas por cierto guionista.

SELECT peliculas.nombre,
guionistas.nombre || ' ' || guionistas.apellido AS 'Nombre guionista'
FROM guionistas
JOIN escriben ON guionistas.id = escriben.id
JOIN peliculas ON escriben.id_pel = peliculas.id_pel
GROUP BY guionistas.id_pel
ORDER BY guionistas.apellido

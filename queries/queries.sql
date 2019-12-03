.open sample.db

#Películas por director

SELECT peliculas.nombre,
directores.nombre || ' ' || directores.apellidos
FROM peliculas
JOIN dirige
ON peliculas.id_pel = dirige.id_pel
JOIN directores
ON dirige.id_dir = directores.id_dir
ORDER BY directores.apellidos

#Películas por duración

SELECT peliculas.nombre,
peliculas.duracion
FROM Películas
ORDER BY peliculas.duracion

#Información de las películas

SELECT peliculas.nombre,
peliculas.año,
peliculas.duracion,
peliculas.rating
FROM peliculas
ORDER BY peliculas.nombre

#Películas por categoría (de edad)

SELECT peliculas.nombre,
calificacion.nombre
FROM peliculas
JOIN adecuado_para
ON peliculas.id_pel = adecuado_para.id_pel
JOIN calificacion
ON adecuado_para.id_cal  = calificacion.id_calificacion
ORDER BY peliculas.nombre

#Películas de un actor de cierto género

SELECT peliculas.nombre ,
actores.nombre || ' ' || actores.apellidos,
generos.nombre
FROM peliculas
JOIN trabajan
ON peliculas.id_pel = trabajan.id_pel
JOIN actores
ON  trabajan.id_act = actores.id_act
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

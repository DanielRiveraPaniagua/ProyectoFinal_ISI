#peliculas por calificación

SELECT
Peliculas.nombre, Calificacion.nombre
from Peliculas
join adecuado_para as a
on a.id_pelicula = Peliculas.id_pelicula
join Calificacion
on a.id_edades = Calificacion.id_edades

#peliculas por genero

SELECT
Peliculas.nombre, Genero.nombre
from Peliculas
join pertenece as p
on Peliculas.id_pelicula = p.id_pelicula
join Generos
on p.id_genero = Generos.id_genero

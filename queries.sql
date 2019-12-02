
SELECT
m.nombre, c.nombre
from peliculas as m
join adecuado_para as a
on a.id_pelicula = m.id_pelicula
join calificacion as c 
on a.id_calificacion = c.id_calificacion

SELECT
m.nombre, g.nombre
from peliculas as m
join pertenece as p
on m.id_pelicula = p.id_pelicula
join generos as g
on p.id_genero = g.id_genero

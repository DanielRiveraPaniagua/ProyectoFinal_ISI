SELECT
p.nombre, c.nombre
from peliculas as p
join adecuado_para as a
on a.id_pelicula = p.id_pelicula
join calificacion as c 
on a.id_calificacion = c.id_calificacion


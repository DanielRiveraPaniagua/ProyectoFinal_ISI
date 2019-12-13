FORMATO DE LOS FICHEROS DE DATOS LIMPIOS
------------------------------------------

*actores.txt

{Apellido}.{Nombre},{idpelicula}
	
	-Donde id_pelicula es la pelicula con la que esta asociada esa entrada para este actor. Por 		tanto en este fichero puede aparecer varias veces el mismo actor con distinto id_pelicula.

*peliculas.txt

{id_pelicula}. {Nombre}. {fecha de salida}

*id_peli_actor.txt
FORMATO:
{id_pelicula} .{id_actor}
Fichero de información que relaciona las peliculas en las que ha trabajado el actor 


*rating.txt
FORMATO:
{id_pelicula}. {rating}. {num_votos}
Fiecho que contiene el id con cada una de las valoraciones y los votos

*id_500_mejores.txt
Fichero que obtiene las 500 mejores peliculas por rating
para ello se ha empleado la querie---->
SELECT * FROM RATING
ORDER BY FIELD2 DESC limit 500

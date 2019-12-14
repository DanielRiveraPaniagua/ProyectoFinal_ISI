* FORMATO FICHEROS.TXT *

 - datos_peliculas_FINAL.txt
	-Tiene el siguiente formato, CADA CAMPO SEPARADO POR TABULADORES: id_imdb id_pedro titulo año
	-Son unas 197.000 películas.
	-Queda pendiente borrar el peliculas.txt anterior, cuando confirmemos que estos son los datos sobre los que queremos trabajar.

 - PELICULAS.TXT -> ID_FILM~TITLE~YEAR
 
	1) Contiene las peliculas aportadas por el fichero de Pedro con los id de IMDb.
	2) Id_film sacado de IMDb para poder relacionarlo con la informacion de IMDb.
 De esta forma podremos relacionar por ejemplo, los directores de una pelicula, debido
 a que en IMDb en title.crew.tsv, solo disponemos del id_film y id_director, por tanto
 usar para peliculas id_film de IMDb facilita la relaciones con datos de IMDb.
 Al igual pasa con el resto de tablas.

 - PERSONAS.TXT -> FALTA POR PROCESAR.
	1) Se dispone en FilesToProcessData de un scrip para poder procesar estos datos.
	2) Habria que realizar unos pequeños cambios para diferenciar entre 3 las tres profesiones.



*Relacion_id_peli_actor.txt
FORMATO:
{id_pelicula} .{id_actor}

QUERIE:
SELECT ID_500.FIELD1,ID_PELI_ACTOR.FIELD2 FROM ID_500
join ID_PELI_ACTOR ON ID_500.field1=ID_PELI_ACTOR.FIELD1 


*peliculas_COMPLETO.txt

las 500 mejores peliculas por rating, con su campo rating y num_votos
{id_pelicula}. {titulo} { año}{esAdulta} {rating} {num_votos}
he tenido que separarlo por pipes, porque por tabuladores no se separaba bien el num_votos

QUERIE:
SELECT pelis.field1,pelis.field3,pelis.field4,info.isadult,rating.field2,rating.field3 FROM PELIS
inner JOIN rating on rating.field1=pelis.field1
inner join info on info.tconst=rating.field1
order by rating.field2 desc ,
 rating.field3 desc limit 500

comando sh, para ver si hay repetido : awk '!NF || !seen[$0]++' pelis_v.txt > pelis_norepe.txt



*actors.txt
{id_actor}.{nombre y apellidos} {año_nacimiento}{año_muerte}
todos los actores de las peliculas que elegimos (las 500 mejores)

*peliculas_actor_info.txt
{id_pelicula}.{id_actor}.{nombre y apellidos} {año_nacimiento}{año_muerte}
lo mismo que el anterior pero además con el id de la pelicula por si fuera de utilidad

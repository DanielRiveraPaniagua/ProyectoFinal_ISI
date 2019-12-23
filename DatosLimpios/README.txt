* FORMATO FICHEROS.TXT *


 - PELICULAS.TXT

	las 500 mejores peliculas por rating, con su campo rating y num_votos
	{id_pelicula}. {titulo} { año}{esAdulta} {rating} {num_votos}{duracion}
	he tenido que separarlo por pipes, porque por tabuladores no se separaba bien el num_votos. Donde el id_pelicula hace relación al id de IMDb

	QUERIE:
	SELECT pelis.field1,pelis.field3,pelis.field4,info.isadult,rating.field2,rating.field3,pelis.duracion FROM PELIS
	inner JOIN rating on rating.field1=pelis.field1
	inner join info on info.tconst=rating.field1
	order by rating.field2 desc ,
	 rating.field3 desc limit 500


	comando sh, para ver si hay repetido : awk '!NF || !seen[$0]++' pelis_v.txt > pelis_norepe.txt



*Relacion_id_peli_actor.txt
	FORMATO:
	{id_pelicula} .{id_actor} Donde el id_pelicula hace relación al id de IMDb

	QUERIE:
	SELECT ID_500.FIELD1,ID_PELI_ACTOR.FIELD2 FROM ID_500
	join ID_PELI_ACTOR ON ID_500.field1=ID_PELI_ACTOR.FIELD1 



*actors.txt
	{id_actor}.{nombre y apellidos} {año_nacimiento}{año_muerte}
	todos los actores de las peliculas que elegimos (las 500 mejores)

*peliculas_actor_info.txt
	{id_pelicula}.{id_actor}.{nombre y apellidos} {año_nacimiento}{año_muerte}
	lo mismo que el anterior pero además con el id de la pelicula por si fuera de utilidad. Donde el id_pelicula hace relación al id de IMDb


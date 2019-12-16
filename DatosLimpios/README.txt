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


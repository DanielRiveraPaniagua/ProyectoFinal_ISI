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

-----------------------------------------------------------------------
Peliculas: 197971

Nº directores: 53117
Nº relaciones directores_peliculas: 214531
En la relacion se contemplan todas las peliculas para las cuales haya:
ningun director (\N), un director o más.

Nº guionstas: 38634
Nº relaciones guionistas_peliculas: 327064
En la relacion se contemplan todas las peliculas para las cuales haya:
ningun guionista (\N), un guionista o más.

Nº generos: 29(contando tambien el \N)
Nº relaciones peliculas_generos: 335445

******************
* directores.txt *
******************
nconst	primaryName	birthyear	deathyear

nconst: id_director
primaryName: Nombre y Apellidos
birthyear: Año de nacimiento
deathyear: Año de muerte

*************************************
* relacion_directores_peliculas_final.txt *
*************************************
tconst	directors

tconst: id_pelicula
director: id_director

******************
* guionistas.txt *
******************
nconst	primaryName	birthyear	deathyear

nconst: id_guionista
primaryName: Nombre y Apellidos
birthyear: Año de nacimiento
deathyear: Año de muerte

*************************************
* relacion_guionistas_peliculas.txt *
*************************************
tconst	writers

tconst: id_pelicula
writers: id_guionista

*************************************
*  relacion_peliculas_generos.txt   *
*************************************
tconst  genres

tconst: id_pelicula
genres: género

******************
*   generos.txt  *
******************
genres

genres: género

La separacion entre atributos es un tabulador.

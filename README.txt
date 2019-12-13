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

Informacion para nosotros Felipe (luego hay que quitarla cuando todo ok):
Peliculas: 197971

Nº directores sin LIKE en query: 57081
Nº directores con LIKE en query: 53117
Nº relaciones directores_peliculas: 922307

Nº guionistas sin LIKE en query: 43841
Nº guionstas con LIKE en query: 38634
Nº relaciones guionistas_peliculas:294536

******************
* directores.txt *
******************
nconst	primaryName	birthyear	deathyear

nconst: id_director
primaryName: Nombre y Apellidos
birthyear: Año de nacimiento
deathyear: Año de muerte

*************************************
* relacion_directores_peliculas.txt *
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

La separacion entre atributos es un tabulador.

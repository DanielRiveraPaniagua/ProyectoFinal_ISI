#!/bin/bash

sed 's/ //g' peliculas.txt > peliculas_prueba_2.txt

awk -F"," '{print "INSERT INTO Peliculas(id_pelicula,nombre,anyo,duracion,rating) VALUES(" $1 ","  "\"" $2 "\"," $3  "," """\"undefined" "\"," undefined" "");"}' peliculas_prueba_2.txt > peliculas_prueba_3.txt


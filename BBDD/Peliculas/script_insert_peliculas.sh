#!/bin/bash

#sed 's/ //g' peliculas.txt > peliculas_1.txt

#sed s/"'"/" "/g peliculas_1.txt > peliculas_2.txt

awk -F"|" '{print "INSERT INTO Peliculas(id_pelicula,nombre,anyo,duracion,rating) VALUES(" $1 ","  "\"" $2 "\"," $3  "," """\"undefined" "\","  "0" ");"}' peliculas_2.txt > insert.txt


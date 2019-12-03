#!/bin/bash

awk -F"," '{print "INSERT INTO Peliculas(id_pelicula,nombre,anyo,duracion,rating) VALUES(" $1 ","  "\"" $2 "\"," $3  "," """\"undefined" "\","  "undefined" ");"}' peliculas.txt > insert.txt


#!/bin/bash

sed 's/ //g' actores.txt > actores_bueno.txt

awk -F"," 'BEGIN{count=1} {print "INSERT INTO Actores(apellido,nombre,id_pelicula,id_actor,pais_de_origen, fecha_nacimiento,fecha_muerte) VALUES(""\"" $1 "\","  "\"" $2 "\"," $3  "," count++ "," """\"undefined" "\"," "\"undefined" "\"," "\"undefined" "\""");"}' actores_bueno.txt > insert.txt

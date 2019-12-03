#!/bin/bash

#sed 's/ //g' actores.txt > actores_bueno.txt

awk -F"," 'BEGIN{count=1}
	{if($3 != NULL)print "INSERT INTO Actores(apellido,nombre,id_pelicula,id_actor,pais_de_origen, fecha_nacimiento,fecha_muerte) VALUES(""\"" $1 "\","  "\"" $2 "\"," $3  "," count++ "," """\"undefined" "\"," "\"undefined" "\"," "\"undefined" "\""");"; else if($3==NULL) print "INSERT INTO Actores(apellido,nombre,id_pelicula,id_actor,pais_de_origen, fecha_nacimiento,fecha_muerte) VALUES(""""\"undefined" "\","  "\"" $1 "\"," $2  "," count++ "," """\"undefined" "\"," "\"undefined" "\"," "\"undefined" "\""");"}' actores.txt > insert.txt

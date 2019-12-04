#!/bin/bash

#sed 's/ //g' actores.txt > actores_bueno.txt

awk -F"," 'BEGIN{count=1}
	{if($3 != NULL)print "\"" $1 "\","  "\"" $2 "\"," $3  "," count++ "," """\"undefined" "\"," "\"undefined" "\"," "\"undefined" "\""; else if($3==NULL) print """\"undefined" "\","  "\"" $1 "\"," $2  "," count++ "," """\"undefined" "\"," "\"undefined" "\"," "\"undefined" "\""}' actores.txt > datos_actores.txt

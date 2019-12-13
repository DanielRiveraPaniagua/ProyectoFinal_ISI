#!/bin/bash

#sed 's/ //g' actores.txt > actores_bueno.txt

awk -F"," 'BEGIN{count=1}
	{if($3 != NULL)print "\"" $1 "\","  "\"" $2 "\"," $3  "," count++ "," "" "\"undefined" "\"," "\"undefined" "\""; else if($3==NULL) print """\"" "\","  "\"" $1 "\"," $2  "," count++ "," "" "\"undefined" "\"," "\"undefined" "\""}' actores.txt > datos_actores.txt

awk -F "," '!x[$1 $2]++' datos_actores.txt > datos_corregido.txt	

#awk 'FNR==NR{a[$1]=$2 FS $3;next}{ print $0, a[$1]}' datos_corregido.txt imdb.txt > salida.txt


awk -F '\t' '{print $2 "," $3 "," $4 }' imdb.txt > salida.txt

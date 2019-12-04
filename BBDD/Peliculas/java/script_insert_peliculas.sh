#!/bin/bash

#sed 's/ //g' peliculas.txt > peliculas_1.txt

#sed s/"'"/" "/g peliculas_1.txt > peliculas_2.txt

awk -F"|" '{print $1 "|"  "\"" $2 "\"|" $3  "|" """\"undefined" "\"|"  "0" }' peliculas.txt > datos_peliculas.txt


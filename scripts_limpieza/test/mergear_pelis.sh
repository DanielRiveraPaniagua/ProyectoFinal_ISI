#!/bin/bash
# Juntamos todos los archivos que contienen peliculas
for archivo in $(ls ../data/datosPeliculasActores/*.txt); do
    join $archivo >> datosMergeados.txt
done

# Eliminamos lineas duplicadas
cat datosMergeados.txt | sort | uniq >> movies.txt

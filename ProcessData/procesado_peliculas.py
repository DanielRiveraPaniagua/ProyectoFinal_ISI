#!/usr/bin/python3.6
import csv

peliculas = open("./peliculas.txt", "r")
movies = open("./movies.txt", "w")

r = csv.reader(peliculas, delimiter = ",")

with open('title.basics.tsv','r') as tsv:
    re = csv.reader(tsv, delimiter = "\t")
    for row in r:
        title = row[1]
        for fila in re:
            if title == fila[3]:
                id = fila[0]
                fecha = row[2]
                movies.write(str(id) + ',' + title + ',' + fecha + '\n')



peliculas.close()
movies.close()

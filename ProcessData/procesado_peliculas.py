#!/usr/bin/python3.6
import csv

peliculas = open("./peliculas.txt", "r")
movies = open("./movies.txt", "w")

r = csv.reader(peliculas, delimiter = ",")


for row in r:
n = 0
title = row[1]
print(title)
with open('title.basics.tsv','r') as tsv:
    re = csv.reader(tsv, delimiter = "\t")
    for fila in re:
        print("->" + fila[3])
        if title == fila[3]:
            #print("entramos")
            id = fila[0]
            fecha = row[2]
            movies.write(str(id) + ',' + title + ',' + fecha + '\n')
            break

peliculas.close()
movies.close()

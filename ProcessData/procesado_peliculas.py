#!/usr/bin/python3.6
import csv

peliculas = open("./peliculas.txt", "r")
movies = open("./movies.txt", "w")

r = csv.reader(peliculas, delimiter = ",")

with open('title.basics.tsv','r') as tsv:
    re = csv.reader(tsv, delimiter = "\t")
    for row in r:
        n = 0
        title = row[1]
        print(title)
        for fila in re:
            # No encuentra la pelicula y solo da una vuelta dentro
            #n += 1
            #if n == 1:
                #print(fila)
            if title == fila[3]:
                #print("entramos")
                id = fila[0]
                fecha = row[2]
                movies.write(str(id) + ',' + title + ',' + fecha + '\n')
                break



peliculas.close()
movies.close()

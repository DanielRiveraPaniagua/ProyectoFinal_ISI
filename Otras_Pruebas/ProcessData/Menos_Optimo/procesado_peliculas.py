#!/usr/bin/python3.6
import csv

peliculas = open("./peliculas.txt", "r")
films = open("./films.txt", "w")

r = csv.reader(peliculas, delimiter = "~")

#n = 0
for row in r:
    title = row[0]
    # Elimino caracter en blanco del final para poder comparar adecuadamente
    title = title.strip(' ')
    fecha = row[1]
    #print(len(title))
    #print(title)
    #if n == 1:
    #    break
    #n += 1
    with open('title.basics.tsv','r') as tsv:
        re = csv.reader(tsv, delimiter = "\t")
        for fila in re:
            if title == fila[3]:
                print("Entramos")
                id = fila[0]
                films.write(str(id) + '~' + title + '~' + fecha + '\n')
                break


peliculas.close()
films.close()

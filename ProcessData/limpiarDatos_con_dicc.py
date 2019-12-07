#!/usr/bin/python3.6
import csv
films = open("./films.txt", "w")

n = 0
dict_movies = {}
dict_title_basics = {}

with open("./movies.txt", 'r') as reader:
    for line in reader:
        #n += 1
        pelicula = line.split("/")[0].split("(")
        titulo = pelicula[0]
        titulo = titulo.strip(' ')
        fecha = pelicula[1].replace(')','')
        #print(titulo + "-" + fecha)
        dict_movies[titulo] = fecha
        #if n == 3:
            #break
n = 0
with open('title.basics.tsv','r') as tsv:
        re = csv.reader(tsv, delimiter = "\t")
        for fila in re:
            #n += 1
            #print(fila)
            id = fila[0]
            titulo = fila[3]
            dict_title_basics[titulo] = id
            #if n == 3:
               # break

for key in dict_movies:
    for key2 in dict_title_basics:
        if key == key2:
            print("Entramos")
            id = dict_title_basics[key]
            title = key
            fecha = dict_movies[key]
            films.write(str(id) + '~' + title + '~' + fecha + '\n')
            break
films.close()

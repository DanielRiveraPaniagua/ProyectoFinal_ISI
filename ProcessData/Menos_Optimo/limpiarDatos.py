#!/usr/bin/python3.6
peliculas = open("./peliculas.txt", "w")

def extraer_peliculas(line):
    pelicula = line.split("/")[0].split("(")
    return pelicula[0] + '~' + pelicula[1].replace(')','') + '\n'

with open("./movies.txt", 'r') as reader:
    for line in reader:
        peliculas.write(extraer_peliculas(line))

peliculas.close()

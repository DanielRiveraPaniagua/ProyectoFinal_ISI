#!/usr/bin/python3.6
peliculas = open("./peliculas.txt", "w")

n = 0
dict = {}
with open("./movies.txt", 'r') as reader:
    for line in reader:
        n += 1
        pelicula = line.split("/")[0].split("(")
        titulo = pelicula[0]
        titulo = titulo.strip(' ')
        fecha = pelicula[1].replace(')','')
        dict['titulo'+str(n), 'fecha'+str(n)] =  titulo, fecha
        if n == 3:
            break

a = dict.values()
print(a)
print(dict)
peliculas.close()

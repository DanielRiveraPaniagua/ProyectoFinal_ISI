#!/usr/bin/python3.6
peliculas = open("./peliculas.txt", "w")
actores = open("./actores.txt", "w")

"""
Esta funciona extrae las peliculas con su fecha y agrega un indice unico a cada
peliculas
"""
def extraer_peliculas(line, id_pelicula):
    pelicula = line.split("/")[0].split("(")
    return str(id_pelicula) + ',' + pelicula[0] + ',' + pelicula[1].replace(')','') + '\n'

"""
Esta funcion extrae los nombres y apellidos de los actores que han trabajado en
cada pelicula
"""
def extraer_actores(line, id_pelicula):
    salida = ''
    elementos = line.split("/")[1:]
    for actor in elementos:
        salida += actor.replace("\n","") + ',' + str(id_pelicula) + '\n'
    return salida

id_pelicula = 1
with open("./movies.txt", 'r') as reader:
    for line in reader:
        # print(extraer_peliculas(line, id_pelicula))
        # print(extraer_actores(line, id_pelicula))
        peliculas.write(extraer_peliculas(line, id_pelicula))
        actores.write(extraer_actores(line, id_pelicula))
        id_pelicula += 1
        actores



peliculas.close()
actores.close()

#!/usr/bin/python3.6
import csv

generos = open("./generos.txt", "w")

def extraer_generos(line, id_pelicula):
    cadena = ""
    print(id_pelicula)
    if id_pelicula == 1102256:
        print("entra")        
        print(line[0:])
    try:
        gs = line[8].split(',')
    except IndexError:
        return cadena
    
    #print(line[8].split(','))
    for g in gs:
        cadena = cadena + str(id_pelicula) + ',' + line[0] + ',' + g + '\n'
        #print("->" + cadena)
    return cadena

with open("./title.basics.tsv", 'r') as reader:
    n = 0
    id_pelicula = 0
    
    tsvreader = csv.reader(reader, delimiter="\t")
    for line in tsvreader:
        if id_pelicula != 0:
            generos.write(extraer_generos(line, id_pelicula))
        n=n+1
        #limitador de datos
        if n == 10000:
           break
        id_pelicula = id_pelicula + 1

generos.close()


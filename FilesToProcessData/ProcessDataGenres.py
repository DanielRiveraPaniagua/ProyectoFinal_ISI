#!/usr/bin/python3.6
import csv

generos = open("./generos.txt", "w")

def extraer_generos(line):
    cadena = ""
    
    with open("../ProcessData/films.txt", 'r') as reader:
        for data in reader:
            data = data.split('~')
            if line[0] == data[0]:
                #print("entra")
                try:
                    gs = line[8].split(',')
                except IndexError:
                    #print(line[0:])
                    return cadena

                for g in gs:
                    cadena = cadena + str(line[0]) + '~' + g + '\n'
                    #print("->" + cadena)
                return cadena
                break
    return cadena

with open("./title.basics.tsv", 'r') as reader:
    n = 0
    
    tsvreader = csv.reader(reader, delimiter="\t")
    for line in tsvreader:
        if n != 0:
            generos.write(extraer_generos(line))
        n=n+1
        #limitador de datos de salidad
        
        #if n == 10:
           # break

generos.close()


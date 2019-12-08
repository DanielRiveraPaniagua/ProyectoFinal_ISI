#!/usr/bin/python3.6
import csv

############### Formato de tsv ###############
# id [0], escritores [1], guionistas [2]
##############################################
dict_movies_ids = {}
dict_title_crew = {}

directores = open("./directores.txt", "w")
guionistas = open("./guionistas.txt", "w")

def extraer_datos(row, id):
    if id != 0:
        out = ""
        b = row.split(",")
        if(len(b) > 1):
            for p in b:
                out += (str(id) + ',' + p + '\n')
        else:
            return str(id) + ',' + b[0] + '\n'
        return out
    else:
        return ""


with open("./films.txt", 'r') as reader:
    n = 0
    for line in reader:
        id = line[0]
        dict_movies_ids[id] = id
        n += 1
        if n == 3:
            break





directores.close()
guionistas.close()

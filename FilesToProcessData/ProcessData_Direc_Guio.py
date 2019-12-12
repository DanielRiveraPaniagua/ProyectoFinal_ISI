#!/usr/bin/python3.6
import csv

############### Formato de tsv ###############
# id [0], escritores [1], guionistas [2]
##############################################
dict_movies_ids = {}
dict_directores = {}
dict_guionistas = {}

directores = open("./directores.txt", "w")
guionistas = open("./guionistas.txt", "w")

def extraer_datos(id_dir, id_film):
    #if id != 0:
    out = ""
    b = id_dir.split(",")
    if(len(b) > 1):
        for p in b:
            out += (str(id_film) + ',' + p + '\n')
    else:
        return str(id_film) + ',' + b[0] + '\n'
    return out
    #else:
    #    return ""


with open("./films.txt", 'r') as reader:
    #n = 0
    for line in reader:
        id = line.split('~')[0]
        dict_movies_ids[id] = id
        #print(id)
        #print(dict_movies_ids)
        #n += 1
        #if n == 1:
        #    break

with open('title.crew.tsv','r') as tsv:
    r = csv.reader(tsv, delimiter = "\t")
    for row in r:
        #n += 1
        #print(row)
        id = row[0]
        id_director = row[1]
        id_guionista = row[2]
        #print("id: " + id)
        #print("id_director: " + id_director)
        dict_directores[id] = id_director
        dict_guionistas[id] = id_guionista
        #if n == 30:
         #  break

for key in dict_movies_ids:
    for key2 in dict_directores:
        if key == key2:
            print("Entramos")
            id_dir = dict_directores[key]
            id_gui = dict_guionistas[key]
            #print("id_dir: " + id_dir)
            id_film = dict_movies_ids[key]
            #print("id_film: " + id_film)
            directores.write(extraer_datos(id_dir, id_film))
            guionistas.write(extraer_datos(id_gui, id_film))
            break

directores.close()
guionistas.close()

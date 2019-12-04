#!/usr/bin/python3.6
import csv

############### Formato de tsv ###############
# id [0], escritores [1], guionistas [2]
##############################################
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

directores = open("./directores.txt", "w")
guionistas = open("./guionistas.txt", "w")

with open('title.crew.tsv','r') as tsv:
    id = 0;
    lines = 1
    r = csv.reader(tsv, delimiter = "\t")
    for row in r:
        directores.write(extraer_datos(row[1], id))
        guionistas.write(extraer_datos(row[2], id))
        lines+=1
        id += 1
        # Delimitacion orientativa
        if lines >= 9000:
            break


directores.close()
guionistas.close()

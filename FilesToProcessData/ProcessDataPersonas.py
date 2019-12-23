#!/usr/bin/python3.6
import csv

############### Formato de tsv ###############
# id [0], primaryName [1], birthYear [2], deathYear[3],
# primaryProfession[4], knownForTitles[5]
##############################################
def extraer_datos(row):
    out = ""
    primaryProfession = row[4].split(",")
    knownForTitles = row[5].split(",")
    if(len(primaryProfession) > 1 or len(knownForTitles) > 1):
        for p in primaryProfession:
            for k in knownForTitles:
                out += (row[0] + '~' + row[1] + '~' + row[2] + '~' + row[3] + '~' + p + '~' + k + '\n')
    else:
        return (row[0] + '~' + row[1] + '~' + row[2] + '~' + row[3] + '~' + primaryProfession[0] + '~' + knownForTitles[0] + '\n')
    return out


personas = open("./personas.txt", "w")

with open('name.basics.tsv','r') as tsv:
    lines = 1
    r = csv.reader(tsv, delimiter = "\t")
    for row in r:
        personas.write(extraer_datos(row))
        lines += 1
        # Delimitacion orientativa
        if lines >= 15000:
            break


personas.close()

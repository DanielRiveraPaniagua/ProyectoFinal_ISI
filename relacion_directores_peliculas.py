directores = open("./relacion_directores_guionistas_sin_split.txt", "r")
relacion_directores_peliculas = open("./relacion_directores_peliculas.txt", "w")

def extraer_datos(id_film, id_dir):
    n = 1
    out = ""
    b = id_dir.split(",")
    if(len(b) > 1):
        for p in b:
            if(len(b)) == n:
                out += (str(id_film) + '    ' + p)
            else:
                out += (str(id_film) + '    ' + p + "\n")
            n += 1
    else:
        return str(id_film) + ' ' + b[0]
    return out


for line in directores:
    line = line.split("\t")
    #print(line[0], line[1])
    relacion_directores_peliculas.write(extraer_datos(line[0], line[1]))

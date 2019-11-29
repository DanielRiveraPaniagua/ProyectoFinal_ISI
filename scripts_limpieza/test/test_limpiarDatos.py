#!/usr/bin/python3.6
# -*- coding: utf-8 -*-
import unittest
import limpiarDatos


class TestLimpiarDatos(unittest.TestCase):



    
    def test_extraer_peliculas_1(self):
        with open("./movies.txt", 'r') as reader:  
            lines = [line.strip() for line in reader]
        pelicula = lines[0]
        id_pelicula = 1
        self.assertEqual(limpiarDatos.extraer_peliculas(pelicula, id_pelicula),'1,00:01:18 ,2007' +'\n')

    def test_extraer_peliculas_2(self):
        with open("./movies.txt", 'r') as reader:  
            lines = [line.strip() for line in reader]
        pelicula = lines[1]
        id_pelicula = 2
        self.assertEqual(limpiarDatos.extraer_peliculas(pelicula, id_pelicula),'2,002 agenti segretissimi ,1964' +'\n')

    def test_extraer_actores_1(self):
        with open("./movies.txt", 'r') as reader:  
            lines = [line.strip() for line in reader]
        pelicula = lines[0]
        id_pelicula = 1
        self.assertEqual(limpiarDatos.extraer_actores(pelicula, id_pelicula),'Darrigo, Paul,1' +'\n' + 'Joëlle, Christine,1' +'\n')

    def test_extraer_actores_2(self):
        with open("./movies.txt", 'r') as reader:  
            lines = [line.strip() for line in reader]
        pelicula = lines[19]
        id_pelicula = 20
        self.assertEqual(limpiarDatos.extraer_actores(pelicula, id_pelicula),'van Kempen, Ad,20' + '\n'+ 'Schluter, Ariane,20' + '\n')
    
                    
                 
if __name__ == "__main__":
    unittest.main()

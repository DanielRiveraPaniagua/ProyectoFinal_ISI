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
  
                    
                 
if __name__ == "__main__":
    unittest.main()

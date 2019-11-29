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

    def test_extraer_peliculas_3(self):
        with open("./movies.txt","r") as reader:
            lines=[line.strip() for line in reader]
        pelicula=lines[2]
        id_pelicula=3
        self.assertEqual(limpiarDatos.extraer_peliculas(pelicula, id_pelicula),'3,002 operazione Luna ,1965' +'\n')

    def test_extraer_peliculas_4(self):
        with open("./movies.txt","r") as reader:
            lines=[line.strip() for line in reader]
        pelicula=lines[3]
        id_pelicula=4
        self.assertEqual(limpiarDatos.extraer_peliculas(pelicula, id_pelicula),'4,007 1 2 no Carnaval ,1966' +'\n')  

    def test_extraer_peliculas_5(self):
        with open("./movies.txt","r") as reader:
            lines=[line.strip() for line in reader]
        pelicula=lines[4]
        id_pelicula=5
        self.assertEqual(limpiarDatos.extraer_peliculas(pelicula, id_pelicula),'5,007 in Rio ,1979' +'\n')        
      
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

    def test_extraer_actores_3(self):
        with open("./movies.txt", 'r') as reader:  
            lines = [line.strip() for line in reader]
        pelicula = lines[7]
        id_pelicula = 8
        self.assertEqual(limpiarDatos.extraer_actores(pelicula, id_pelicula),'Serato, Massimo,8' + '\n') 

    def test_extraer_actores_4(self):
        with open("./movies.txt", 'r') as reader:  
            lines = [line.strip() for line in reader]
        pelicula = lines[12]
        id_pelicula = 13
        self.assertEqual(limpiarDatos.extraer_actores(pelicula, id_pelicula),'Heinze, Thomas,13' + '\n' + 'Kullik, Silke,13' + '\n')   

    def test_extraer_actores_5(self):
        with open("./movies.txt", 'r') as reader:  
            lines = [line.strip() for line in reader]
        pelicula = lines[25]
        id_pelicula = 26
        self.assertEqual(limpiarDatos.extraer_actores(pelicula, id_pelicula),'McIness, Scott,26' + '\n')            
    
    
if __name__ == "__main__":
    unittest.main()

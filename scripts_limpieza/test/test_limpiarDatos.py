#!/usr/bin/python3.6
# -*- coding: utf-8 -*-
import unittest
import limpiarDatos


class TestLimpiarDatos(unittest.TestCase):
    
    def test_extraer_peliculas(self):
        self.assertEqual(limpiarDatos.extraer_peliculas(1, 1), '1,00:01:18 ,2007' + '\n')

if __name__ == "__main__":
    unittest.main()

#!/usr/bin/env python
# -*- coding: utf-8 -*-

from fractions import Fraction
from math import fmod

__author__ = "Lázaro Pérez David Jonathan, Licona Gómez Aldo Daniel and Marín Parra José Guadalupe de Jesús"

'''Calcula el mod'''
def calculaMod(x, mod, switch):
    resultado = 0
    if switch == 0:
        resultado = fmod(x, mod)
        if(resultado < 0 and resultado < mod): 
            resultado = resultado + mod
    elif switch == 1:
        for i in range(1,mod):
            modax = fmod(x.denominator * i, mod)
            if(modax < 0 and modax < mod):
                modax = modax + mod
            modax1 = fmod(x.numerator, mod)
            if(modax1 < 0 and modax1 < mod):
                modax1 = modax1 + mod
            if(modax == modax1):
                resultado = i
                break
    return resultado

'''Calcula el mod inverso'''
def modInverso(a, m) : 
    a = a % m 
    for x in range(1, m) : 
        if ((a * x) % m == 1) : 
            return x 
    return 1
#!/usr/bin/env python
# -*- coding: utf-8 -*-

from fractions import Fraction
from math import fmod
from mod import calculaMod

__author__ = "Lázaro Pérez David Jonathan, Licona Gómez Aldo Daniel and Marín Parra José Guadalupe de Jesús"

'''Agrega los dos puntos de la Curva'''
def agregaPuntos(potenciaX, potenciaY, a, mod, x1, y1, x2, y2):
    if x1 == x2 and y1 == y2:
        calculo_arriba = (potenciaX*(x1**(potenciaX-1))+a)
        calculo_abajo = (potenciaY*(y1**(potenciaY-1)))
        switch = 0
        if calculo_arriba == 0:
            switch = 1
        elif calculo_abajo == 0:
            return 0,0
        else:
            if (calculo_arriba % calculo_abajo == 0):
                switch1 = 0
                fraccion = calculo_arriba/calculo_abajo
            else:
                switch1 = 1
                calculo_arriba = int(fmod(calculo_arriba, mod))
                calculo_abajo = int(fmod(calculo_abajo, mod))
                fraccion = Fraction(calculo_arriba, calculo_abajo)
        
        if switch == 1:
            pot = 0
        else:
            pot = calculaMod(fraccion, mod, switch1)
        print("Lambda: ",pot)

        xNew = (pot**2) - x1 -x2
        xNew = calculaMod(xNew, mod, switch1=0)
        yNew = pot*(x1 - xNew) - y1
        yNew = calculaMod(yNew, mod, switch1=0)
        
        return (xNew,yNew)

    else:
        calculo_arriba = (y2 - y1)
        calculo_abajo = (x2 - x1)

        print(calculo_arriba, calculo_abajo)

        if calculo_arriba < 0 and calculo_abajo < 0:
            calculo_arriba = abs(calculo_arriba)
            calculo_abajo = abs(calculo_abajo)

        print(calculo_arriba, calculo_abajo)

        switch2 = 0

        if calculo_arriba == 0:
            switch2 = 1
        elif calculo_abajo == 0:
            return 0,0
        else:
            if (calculo_arriba % calculo_abajo == 0):
                switch1 = 0
                fraccion = calculo_arriba/calculo_abajo
            else:
                switch1 = 1
                calculo_arriba = int(fmod(calculo_arriba, mod))
                calculo_abajo = int(fmod(calculo_abajo, mod))
                fraccion = Fraction(calculo_arriba, calculo_abajo)
        
        if switch2 == 1:
            pot = 0
        else:
            pot = calculaMod(fraccion, mod, switch1)
        print("x1 != x2 La Lambda es: "+str(pot))
        xNew = (pot**2) - x1 -x2
        xNew = calculaMod(xNew, mod, switch1=0)
        yNew = pot*(x1 - xNew) - y1
        yNew = calculaMod(yNew, mod, switch1=0)
        
        return (xNew,yNew)
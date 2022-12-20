#!/usr/bin/env python
# -*- coding: utf-8 -*-

from agregaPuntos import agregaPuntos

__author__ = "Lázaro Pérez David Jonathan, Licona Gómez Aldo Daniel and Marín Parra José Guadalupe de Jesús"

'''Procesa las llaves y datos de la curva'''
def generaxy(n, powX, powY, a, modBy, x1, y1):
    xNew = x1
    yNew = y1
    for i in range(0, n-1):
            if xNew == 0 and yNew == 0:
                    xNew, yNew = x1,y1
            else:
                    xNew, yNew = agregaPuntos(powX, powY, a, modBy, x1, y1, xNew, yNew)
    return xNew, yNew
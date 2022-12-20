#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Proyecto 2 - RSA
------------
"""
__author__ = "Lázaro Pérez David Jonathan, Licona Gómez Aldo Daniel and Marín Parra José Guadalupe de Jesús"

import random 
from random import randint as rand

def verifica_primos(n):
    """Verifica que p y q sean primos diferentes."""
    cont = 0
    x = 2
    if n >= 2:
        while x <= n/2:
            if n%x == 0:
                cont = cont+1
                x = x+1
            else:
                x = x+1
        if cont == 0:
            return True
        else:
            return False
    else:
        return False

def val_e(phi):
    """Calcula el valor de e."""
    e = 2
    valores_e = []
    while e > 1 and e < phi:
        if mcd(e,phi) == 1:
            valores_e.append(e)
            e = e+1
        else:
            e = e+1
    e = valores_e[0]
    return e

def mcd(e,phi):
    """Calcula ⟮𝑒,𝜑⟮𝑛⟯⟯﹦𝟷."""
    m = phi%e
    while m != 0:
        phi = e
        e = m
        m = phi%e
    return e

def val_d(e,phi):
    """Calcula el valor de d."""
    k = 1
    m = (1+(k)*(phi)) % (e)
    while m != 0:
        k = k+1
        m = (1+(k)*(phi))%(e)
    d = int((1+(k)*(phi))/(e))
    return d

def genera_claves_PP():
    """Calcula los valores de p,q,e,d,phi para generar la clave publica y privada."""
    p = rand(1,100)
    q = rand(1,100)
    while verifica_primos(p)==False:
        p = rand(1,100)
    while verifica_primos(q)==False or q==p:
        q = rand(1,100)
    n = p*q
    phi = (p-1)*(q-1)
    e = val_e(phi)
    d = val_d(e,phi)
    claves = [n,e,d]
    return claves

def cifra_RSA(mensaje,llave):
    """Cifra un mensaje con RSA."""
    mensaje = mensaje.upper()
    mensaje_convertido = mensaje.split(" ")
    mensajeCifrado = ""
    l = []
    for i in mensaje_convertido:
        pal = cifrapalabra(i,llave)
        l.append(pal)
    for j in l:
        mensajeCifrado = mensajeCifrado+str(j)+" "
    return mensajeCifrado

def cifrapalabra(m,llave):
    """Cifra palabra por palabra."""
    l1 = []
    l2 = []
    n,e = llave
    palabraCifrada = ""
    for i in m:
        x = posicion(i,1)
        l2.append(x)
    for j in l2:
        c = (j**e)%n
        l1.append(c)
    for llave in l1:
        palabraCifrada = palabraCifrada+str(llave)+" "
    return palabraCifrada  
    
def descifra_RSA(mensaje,llave):
    """Descifra un mensaje con RSA."""
    mensaje = mensaje.upper()
    mensaje_convertido = mensaje.split("  ")
    mensajeDescifrado = ""
    l = []
    for i in mensaje_convertido:
        x = descifrarnumero(i,llave)
        l.append(x)
    for j in l:
        mensajeDescifrado = mensajeDescifrado+str(j)+" "
    return mensajeDescifrado

def descifrarnumero(m,llave):
    """Descifra numero por numero."""
    l1 = []
    l2 = []
    n,d = llave
    numeroDescifrado = ""
    mensaje = m.split(" ")
    for i in mensaje:
        x = int(i)
        l2.append(x)
    for j in l2:
        m = (j**d)%n
        l1.append(m)
    for llave in l1:
        l = posicion(llave,2)
        numeroDescifrado = numeroDescifrado+str(l)
    return numeroDescifrado

def posicion(x,op):
    """Calcula la posicion de un elemento para devolverlo como numero o letra."""
    abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
    cont = 0
    if op == 1:
        for i in abc:
            if x == i:
                return cont
            else:
                cont = cont+1
    else:
        for i in abc:
            if x == cont:
                return i
            else:
                cont = cont+1 


if __name__ == "__main__":
    print("\n\t\tProyecto 2 - RSA\n")

    print("ELEMPLO...\nGENERACION DE CLAVES ...")
    claves = genera_claves_PP()
    print(f"CLAVE PUBLICA [{claves[0]},{claves[1]}]")
    print(f"CLAVE PRIVADA [{claves[0]},{claves[2]}]")
    publica = [claves[0],claves[1]]
    privada = [claves[0],claves[2]]

    print("\nCIFRADO DE MENSAJES")
    mensaje = 'HOLA ESTE ES UN MENSAJE PARA CIFRAR CON RSA'
    print(mensaje+"\n")
    mensaje_cifrado = cifra_RSA(mensaje,publica)
    print("Mensaje Cifrado: "+str(mensaje_cifrado))

    print("\nDESCIFRADO DE MENSAJES")
    aux = str(mensaje_cifrado.replace(' ',  '', 0))
    descifrar_ej = aux[:-2]
    mensaje_descifrado = descifra_RSA(descifrar_ej,privada)
    print("Mensaje Descifrado: "+str(mensaje_descifrado))

    print("\n\nGENERACION DE CLAVES ...")
    claves = genera_claves_PP()
    print(f"CLAVE PUBLICA [{claves[0]},{claves[1]}]")
    print(f"CLAVE PRIVADA [{claves[0]},{claves[2]}]")
    publica = [claves[0],claves[1]]
    privada = [claves[0],claves[2]]

    print("\nCIFRADO DE MENSAJES")
    mensaje = input("Mensaje: ")
    mensaje_cifrado = cifra_RSA(mensaje,publica)
    print("Mensaje Cifrado: "+str(mensaje_cifrado))

    print("\nDESCIFRADO DE MENSAJES")
    mensaje_cifrado=input("Mensaje Cifrado : ")
    mensaje_descifrado=descifra_RSA(mensaje_cifrado,privada)
    print("Mensaje Descifrado : "+str(mensaje_descifrado))
from generaxy import generaxy
from math import fmod, sqrt
from mod import calculaMod, modInverso

__author__ = "Lázaro Pérez David Jonathan, Licona Gómez Aldo Daniel and Marín Parra José Guadalupe de Jesús"

'''Encripta en ECIES'''
def ecies():
    potX = int(input("Potencia de X:"))
    potY = int(input("Potencia de Y:"))
    a = int(input('A: '))
    b = int(input('B: '))
    mod = int(input("Modulo: "))
    remitenteKey = int(input("Ingrese la clave del remitente:"))
    destinatariokey = int(input("Ingrese la clave del destinatario:"))
    puntoX = int(input("Ingrese el punto publico X:")) 
    puntoY = int(input("Ingrese el punto publico Y:"))
    msg = int(input("Ingrese el mensaje a cifrar: "))

    qX, qY = generaxy(destinatariokey, potX, potY, a, mod, puntoX, puntoY)
    kPX, kPY = generaxy(remitenteKey, potX, potY, a, mod, puntoX, puntoY)
    kQX, kQY = generaxy(remitenteKey, potX, potY, a, mod, qX, qY)

    for i in range(1,int(((mod-1)/2) + 1)):
        if (i ** 2) % mod == kPY:
            QR = 1

    if (QR == 1):
        compresX = kPX
        compresY = fmod(kPY, 2)
    else:
        compresX = kPX
        compresY = kPY/sqrt(2)
        compresY = fmod(compresY, 2)

    mody = calculaMod((kQX * msg), mod, flag=0)
    msgcifrado = [[compresX, compresY],[mody, 0]]

    print("El mensaje cifrado es:",msgcifrado)
    choice = int(input("Para continuar con la parte de descifrado, ingrese 1:"))
    if choice == 1:
        desencriptarECIES(msgcifrado, destinatariokey, potX, potY, a, b, mod)

'''Desencriptar en ECIES'''
def desencriptarECIES(msgcifrado, destinatariokey, potX, potY, a, b, mod):
    aux = calculaMod((msgcifrado[0][0] ** potX + msgcifrado[0][0] * a + b), mod, flag=0)
    dY = sqrt(aux)

    if calculaMod(dY, 2, flag=0) != msgcifrado[0][1]:
        dY = (-1) * dY

    dX = msgcifrado[0][0]

    sKPX, sKPY = generaxy(destinatariokey, potX, potY, a, mod, dX, dY)

    msg = calculaMod((msgcifrado[1][0] * modInverso(sKPX, mod)), mod, flag=0)
    print("El mensaje es: ",msg)
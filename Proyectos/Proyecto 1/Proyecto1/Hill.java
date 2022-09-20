import java.util.ArrayList;
import java.util.Scanner;
/**
 * Main para probar el Proyecto 2 Parte 2 Hill.
 * @author Lázaro Pérez David Jonathan          No.cta 316059710
 * @author Licona Gomez Aldo Daniel             No.cta 316263863
 * @author Marín Parra José Guadalupe de Jesús  No.cta 316264176  
 */
public class Hill
{
    static String ALPHABET_MINUS = "abcdefghijklmnñopqrstuvwxyz";
    static String ALPHABET_MAYUS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    // METODOS PARA MATRICES 2X2.
    /**
     * Metodo para llenar la matriz 2x2 desde la terminal.
     * @return La matriz 2x2 llenada.
     */
    private static int[][] getclaveMatrix()
    {
        int[][] matriz = new int[2][2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa los valores de la matriz a,b,c,d");
        System.out.print("a: ");
        matriz[0][0] = sc.nextInt();
        System.out.print("b: ");
        matriz[0][1] = sc.nextInt();
        System.out.print("c: ");
        matriz[1][0] = sc.nextInt();
        System.out.print("d: ");
        matriz[1][1] = sc.nextInt();
        return matriz;
    }

    /**
     * Checa que una matriz sea valida.
     * @param clave la clave original de la matriz 2x2.
     * @throws Error Si el determinante es 0.
     */
    private static void validaMatriz(int[][] clave)
    {
        int det = clave[0][0] * clave[1][1] - clave[0][1] * clave[1][0];
        if(det == 0)
            throw new java.lang.Error("El determinante es cero, clave no valida!");
    }

    /**
     * Checa si la inversa de la matriz es valida con el modulo.
     * @param clave la clave original de la matriz 2x2.
     * @param inversa_Matriz la Matriz inversa.
     * @throws Error Si la matriz no tiene inversa.
     */
    private static void checa_inversa(int[][] clave, int[][] inversa_Matriz)
    {
        int[][] prod = new int[2][2];

        prod[0][0] = (clave[0][0]*inversa_Matriz[0][0] + clave[0][1] * inversa_Matriz[1][0]) % 26;
        prod[0][1] = (clave[0][0]*inversa_Matriz[0][1] + clave[0][1] * inversa_Matriz[1][1]) % 26;
        prod[1][0] = (clave[1][0]*inversa_Matriz[0][0] + clave[1][1] * inversa_Matriz[1][0]) % 26;
        prod[1][1] = (clave[1][0]*inversa_Matriz[0][1] + clave[1][1] * inversa_Matriz[1][1]) % 26;

        if(prod[0][0] != 1 || prod[0][1] != 0 || prod[1][0] != 0 || prod[1][1] != 1)
            throw new java.lang.Error("Esta matriz no tiene inversa");
    }

    /**
     * Calcula la inversa de una matriz 2x2.
     * @param clave la clave original de la matriz 2x2.
     * @return la inversa de una matriz 2x2.
     */
    private static int[][] inversa(int[][] clave)
    {
        int detmod26 = (clave[0][0] * clave[1][1] - clave[0][1] * clave[1][0]) % 26;
        int factor;
        int[][] inversa = new int[2][2];

        for(factor=1; factor < 26; factor++)
        {
            if((detmod26 * factor) % 26 == 1)
            {
                break;
            }
        }

        inversa[0][0] = clave[1][1]           * factor % 26;
        inversa[0][1] = (26 - clave[0][1])    * factor % 26;
        inversa[1][0] = (26 - clave[1][0])    * factor % 26;
        inversa[1][1] = clave[0][0]           * factor % 26;

        return inversa;
    }

    // METODOS PARA MATRICES 3X3.
    /**
     * Metodo para llenar la matriz 3x3 desde la terminal.
     * @return La matriz 3x3 llenada.
     */
    private static int[][] getclaveMatrix3()
    {
        int[][] matriz = new int[3][3];
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa los valores de la matriz a,b,c,d,e,f,g,h,i");
        System.out.print("a: ");
        matriz[0][0] = sc.nextInt();
        System.out.print("b: ");
        matriz[0][1] = sc.nextInt();
        System.out.print("c: ");
        matriz[0][2] = sc.nextInt();
        System.out.print("d: ");
        matriz[1][0] = sc.nextInt();
        System.out.print("e: ");
        matriz[1][1] = sc.nextInt();
        System.out.print("f: ");
        matriz[1][2] = sc.nextInt();
        System.out.print("g: ");
        matriz[2][0] = sc.nextInt();
        System.out.print("h: ");
        matriz[2][1] = sc.nextInt();
        System.out.print("i: ");
        matriz[2][2] = sc.nextInt();
        return matriz;
    }

    /**
     * Metodo para sacar la inversa de una matriz 3x3.
     * @param matriz la clave original de la matriz 3x3.
     * @return la inversa de una matriz 3x3.
     */
    public static int[][] matrizInversa(int[][] matriz)
    {
        int det = 1/determinante(matriz);
        int[][] matrizaux = matrizAdjunta(matriz);
        multiplicarMatriz(det,matrizaux);
        return matrizaux;
    }

    /**
     * Metodo para multiplicar una matriz por un entero n.
     * @param matriz la matriz 3x3.
     * @param n el entero para multiplicarlo con la matriz 3x3.
     */
    public static void multiplicarMatriz(int n, int[][] matriz)
    {
        for(int i=0; i<matriz.length; i++)
            for(int j=0; j<matriz.length; j++)
                matriz[i][j]*=n;
    }

    /**
     * Metodo para sacar la matriz adjunta.
     * @param matriz la matriz 3x3.
     * @return la matriz adjunta.
     */
    public static int[][] matrizAdjunta(int [][] matriz)
    {
        return matrizTranspuesta(matrizCofactores(matriz));
    }

    /**
     * Metodo para sacar los cofactores de una matriz 3x3.
     * @param matriz la matriz 3x3.
     * @return cofactores de la matriz 3x3.
     */
    public static int[][] matrizCofactores(int[][] matriz)
    {
        int[][] matrizCof = new int[matriz.length][matriz.length];
        int[][] det = new int[matriz.length-1][matriz.length-1];
        int detValor,indice1,indice2;
        for(int i=0; i<matriz.length; i++)
        {
            for(int j=0; j<matriz.length; j++)
            {
                for(int k=0; k<matriz.length; k++)
                {
                    if(k!=i) {
                        for(int l=0; l<matriz.length; l++)
                        {
                            if(l!=j)
                            {
                                indice1 = (k<i ? k : k-1);
                                indice2 = (l<j ? l : l-1);
                                det[indice1][indice2]=matriz[k][l];
                            }
                        }
                    }
                }
                detValor = determinante(det);
                matrizCof[i][j] = detValor * (int)Math.pow(-1, i+j+2);
            }
        }
        return matrizCof;
    }

    /**
     * Metodo para sacar la transpuesta de una matriz 3x3.
     * @param matriz la matriz 3x3.
     * @return la matriz 3x3 transpuesta.
     */
    public static int[][] matrizTranspuesta(int [][] matriz)
    {
        int[][] matrizTrans = new int[matriz[0].length][matriz.length];
        for(int i=0; i<matriz.length; i++)
        {
            for(int j=0; j<matriz.length; j++)
                matrizTrans[i][j]=matriz[j][i];
        }
        return matrizTrans;
    }

    /**
     * Metodo para sacar el determinante de una matriz 3x3.
     * @param matriz la matriz 3x3.
     * @return el determinante de la matriz 3x3.
     * @throws Error Si el determinante es 0.
     */
    public static int determinante(int[][] matriz)
    {
        int det;
        int suma = 0;
        int[][] nm = new int[matriz.length-1][matriz.length-1];
        int indice = -1;

        if(matriz.length==2)
        {
            det = (matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }

        for(int i=0; i<matriz.length; i++)
        {
            for(int j=0; j<matriz.length; j++)
            {
                if(j!=i){
                    for(int k=1; k<matriz.length; k++)
                    {
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice = j-1;
                            nm[indice][k-1] = matriz[j][k];
                    }
                }
            }

            if(i%2 == 0)
                suma += matriz[i][0] * determinante(nm);
            else
                suma -= matriz[i][0] * determinante(nm);
        }
        return suma;
    }

    /**
     * Imprime el texto descifrado o cifrado.
     * @param texto el texto cifrado o descifrado.
     */
    private static void resultado(ArrayList<Integer> texto)
    {
        System.out.println(texto);
        for(int i=0; i < texto.size(); i+=2)
        {
            switch (texto.get(i))
            {
                case 0: System.out.print(ALPHABET_MAYUS.charAt(0)); break;
                case 1: System.out.print(ALPHABET_MAYUS.charAt(1)); break;
                case 2: System.out.print(ALPHABET_MAYUS.charAt(2)); break;
                case 3: System.out.print(ALPHABET_MAYUS.charAt(3)); break;
                case 4: System.out.print(ALPHABET_MAYUS.charAt(4)); break;
                case 5: System.out.print(ALPHABET_MAYUS.charAt(5)); break;
                case 6: System.out.print(ALPHABET_MAYUS.charAt(6)); break;
                case 7: System.out.print(ALPHABET_MAYUS.charAt(7)); break;
                case 8: System.out.print(ALPHABET_MAYUS.charAt(8)); break;
                case 9: System.out.print(ALPHABET_MAYUS.charAt(9)); break;
                case 10: System.out.print(ALPHABET_MAYUS.charAt(10)); break;
                case 11: System.out.print(ALPHABET_MAYUS.charAt(11)); break;
                case 12: System.out.print(ALPHABET_MAYUS.charAt(12)); break;
                case 13: System.out.print(ALPHABET_MAYUS.charAt(13)); break;
                case 14: System.out.print(ALPHABET_MAYUS.charAt(14)); break;
                case 15: System.out.print(ALPHABET_MAYUS.charAt(15)); break;
                case 16: System.out.print(ALPHABET_MAYUS.charAt(16)); break;
                case 17: System.out.print(ALPHABET_MAYUS.charAt(17)); break;
                case 18: System.out.print(ALPHABET_MAYUS.charAt(18)); break;
                case 19: System.out.print(ALPHABET_MAYUS.charAt(19)); break;
                case 20: System.out.print(ALPHABET_MAYUS.charAt(20)); break;
                case 21: System.out.print(ALPHABET_MAYUS.charAt(21)); break;
                case 22: System.out.print(ALPHABET_MAYUS.charAt(22)); break;
                case 23: System.out.print(ALPHABET_MAYUS.charAt(23)); break;
                case 24: System.out.print(ALPHABET_MAYUS.charAt(24)); break;
                case 25: System.out.print(ALPHABET_MAYUS.charAt(25)); break;
                case 26: System.out.print(ALPHABET_MAYUS.charAt(26)); break;
            }
            switch (texto.get(i+1))
                {
                case 0: System.out.print(ALPHABET_MAYUS.charAt(0)); break;
                case 1: System.out.print(ALPHABET_MAYUS.charAt(1)); break;
                case 2: System.out.print(ALPHABET_MAYUS.charAt(2)); break;
                case 3: System.out.print(ALPHABET_MAYUS.charAt(3)); break;
                case 4: System.out.print(ALPHABET_MAYUS.charAt(4)); break;
                case 5: System.out.print(ALPHABET_MAYUS.charAt(5)); break;
                case 6: System.out.print(ALPHABET_MAYUS.charAt(6)); break;
                case 7: System.out.print(ALPHABET_MAYUS.charAt(7)); break;
                case 8: System.out.print(ALPHABET_MAYUS.charAt(8)); break;
                case 9: System.out.print(ALPHABET_MAYUS.charAt(9)); break;
                case 10: System.out.print(ALPHABET_MAYUS.charAt(10)); break;
                case 11: System.out.print(ALPHABET_MAYUS.charAt(11)); break;
                case 12: System.out.print(ALPHABET_MAYUS.charAt(12)); break;
                case 13: System.out.print(ALPHABET_MAYUS.charAt(13)); break;
                case 14: System.out.print(ALPHABET_MAYUS.charAt(14)); break;
                case 15: System.out.print(ALPHABET_MAYUS.charAt(15)); break;
                case 16: System.out.print(ALPHABET_MAYUS.charAt(16)); break;
                case 17: System.out.print(ALPHABET_MAYUS.charAt(17)); break;
                case 18: System.out.print(ALPHABET_MAYUS.charAt(18)); break;
                case 19: System.out.print(ALPHABET_MAYUS.charAt(19)); break;
                case 20: System.out.print(ALPHABET_MAYUS.charAt(20)); break;
                case 21: System.out.print(ALPHABET_MAYUS.charAt(21)); break;
                case 22: System.out.print(ALPHABET_MAYUS.charAt(22)); break;
                case 23: System.out.print(ALPHABET_MAYUS.charAt(23)); break;
                case 24: System.out.print(ALPHABET_MAYUS.charAt(24)); break;
                case 25: System.out.print(ALPHABET_MAYUS.charAt(25)); break;
                case 26: System.out.print(ALPHABET_MAYUS.charAt(26)); break;
            }
            if(i+2 <texto.size())
                System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Cifra el texto con una matriz 2x2.
     * @param texto El texto a cifrar.
     */
    public static void encrypt(String texto)
    {
        int[][] keyMatrix;
        ArrayList<Integer> phraseToNum = new ArrayList<>();
        ArrayList<Integer> phraseEncoded = new ArrayList<>();

        texto = texto.toUpperCase();
        if(texto.length() % 2 == 1)
            texto += "Q";

        keyMatrix = getclaveMatrix();
        validaMatriz(keyMatrix);

        for(int i=0; i < texto.length(); i++)
        {
            if (texto.charAt(i) == '¤' || texto.charAt(i) == '¥')
                phraseToNum.add(14);
            else
            {
            switch (texto.charAt(i))
            {
                case 'A': phraseToNum.add(0); break;
                case 'B': phraseToNum.add(1); break;
                case 'C': phraseToNum.add(2); break;
                case 'D': phraseToNum.add(3); break;
                case 'E': phraseToNum.add(4); break;
                case 'F': phraseToNum.add(5); break;
                case 'G': phraseToNum.add(6); break;
                case 'H': phraseToNum.add(7); break;
                case 'I': phraseToNum.add(8); break;
                case 'J': phraseToNum.add(9); break;
                case 'K': phraseToNum.add(10); break;
                case 'L': phraseToNum.add(11); break;
                case 'M': phraseToNum.add(12); break;
                case 'N': phraseToNum.add(13); break;
                case 'O': phraseToNum.add(15); break;
                case 'P': phraseToNum.add(16); break;
                case 'Q': phraseToNum.add(17); break;
                case 'R': phraseToNum.add(18); break;
                case 'S': phraseToNum.add(19); break;
                case 'T': phraseToNum.add(20); break;
                case 'U': phraseToNum.add(21); break;
                case 'V': phraseToNum.add(22); break;
                case 'W': phraseToNum.add(23); break;
                case 'X': phraseToNum.add(24); break;
                case 'Y': phraseToNum.add(25); break;
                case 'Z': phraseToNum.add(26); break;
            }
        }
    }
        for(int i=0; i < phraseToNum.size(); i += 2)
        {
            int x = (keyMatrix[0][0] * phraseToNum.get(i) + keyMatrix[0][1] * phraseToNum.get(i+1)) % 26;
            int y = (keyMatrix[1][0] * phraseToNum.get(i) + keyMatrix[1][1] * phraseToNum.get(i+1)) % 26;
            phraseEncoded.add(x == 0 ? 26 : x );
            phraseEncoded.add(y == 0 ? 26 : y );
        }
        resultado(phraseEncoded);
    }

    /**
     * Descifra el texto con una matriz 2x2.
     * @param texto el texto a descifrar.
     */
    public static void decrypt(String texto)
    {
        int[][] keyMatrix, revKeyMatrix;
        ArrayList<Integer> phraseToNum = new ArrayList<>();
        ArrayList<Integer> phraseDecoded = new ArrayList<>();

        texto = texto.toUpperCase();
        keyMatrix = getclaveMatrix();

        validaMatriz(keyMatrix);

        for(int i=0; i < texto.length(); i++)
        {
            if (texto.charAt(i) == '¤' || texto.charAt(i) == '¥')
                phraseToNum.add(14);
            else
            {
            switch (texto.charAt(i))
            {
                case 'A': phraseToNum.add(0); break;
                case 'B': phraseToNum.add(1); break;
                case 'C': phraseToNum.add(2); break;
                case 'D': phraseToNum.add(3); break;
                case 'E': phraseToNum.add(4); break;
                case 'F': phraseToNum.add(5); break;
                case 'G': phraseToNum.add(6); break;
                case 'H': phraseToNum.add(7); break;
                case 'I': phraseToNum.add(8); break;
                case 'J': phraseToNum.add(9); break;
                case 'K': phraseToNum.add(10); break;
                case 'L': phraseToNum.add(11); break;
                case 'M': phraseToNum.add(12); break;
                case 'N': phraseToNum.add(13); break;
                case 'O': phraseToNum.add(15); break;
                case 'P': phraseToNum.add(16); break;
                case 'Q': phraseToNum.add(17); break;
                case 'R': phraseToNum.add(18); break;
                case 'S': phraseToNum.add(19); break;
                case 'T': phraseToNum.add(20); break;
                case 'U': phraseToNum.add(21); break;
                case 'V': phraseToNum.add(22); break;
                case 'W': phraseToNum.add(23); break;
                case 'X': phraseToNum.add(24); break;
                case 'Y': phraseToNum.add(25); break;
                case 'Z': phraseToNum.add(26); break;
            }
        }
    }
        revKeyMatrix = inversa(keyMatrix);
        checa_inversa(keyMatrix, revKeyMatrix);

        for(int i=0; i < phraseToNum.size(); i += 2)
        {
            phraseDecoded.add((revKeyMatrix[0][0] * phraseToNum.get(i) + revKeyMatrix[0][1] * phraseToNum.get(i+1)) % 26);
            phraseDecoded.add((revKeyMatrix[1][0] * phraseToNum.get(i) + revKeyMatrix[1][1] * phraseToNum.get(i+1)) % 26);
        }
        resultado(phraseDecoded);
    }

    /**
     * Cifra el texto con una matriz 3x3.
     * @param texto El texto a cifrar.
     */
    public static void encrypt3(String texto)
    {
        int[][] keyMatrix;
        ArrayList<Integer> phraseToNum = new ArrayList<>();
        ArrayList<Integer> phraseEncoded = new ArrayList<>();

        texto = texto.toUpperCase();
        if(texto.length() % 2 == 1)
            texto += "Q";

        keyMatrix = getclaveMatrix3();

        for(int k=0; k < texto.length(); k++)
        {
            if (texto.charAt(k) == '¤' || texto.charAt(k) == '¥')
                phraseToNum.add(14);
            else
            {
            switch (texto.charAt(k))
            {
                case 'A': phraseToNum.add(0); break;
                case 'B': phraseToNum.add(1); break;
                case 'C': phraseToNum.add(2); break;
                case 'D': phraseToNum.add(3); break;
                case 'E': phraseToNum.add(4); break;
                case 'F': phraseToNum.add(5); break;
                case 'G': phraseToNum.add(6); break;
                case 'H': phraseToNum.add(7); break;
                case 'I': phraseToNum.add(8); break;
                case 'J': phraseToNum.add(9); break;
                case 'K': phraseToNum.add(10); break;
                case 'L': phraseToNum.add(11); break;
                case 'M': phraseToNum.add(12); break;
                case 'N': phraseToNum.add(13); break;
                case 'O': phraseToNum.add(15); break;
                case 'P': phraseToNum.add(16); break;
                case 'Q': phraseToNum.add(17); break;
                case 'R': phraseToNum.add(18); break;
                case 'S': phraseToNum.add(19); break;
                case 'T': phraseToNum.add(20); break;
                case 'U': phraseToNum.add(21); break;
                case 'V': phraseToNum.add(22); break;
                case 'W': phraseToNum.add(23); break;
                case 'X': phraseToNum.add(24); break;
                case 'Y': phraseToNum.add(25); break;
                case 'Z': phraseToNum.add(26); break;
            }
            }
        }

        for(int i=0; i < phraseToNum.size(); i += 3)
        {
            int x = (keyMatrix[0][0] * phraseToNum.get(i) + keyMatrix[0][1] * phraseToNum.get(i+1) + keyMatrix[0][2] * phraseToNum.get(i+2)) % 26;
            int y = (keyMatrix[1][0] * phraseToNum.get(i) + keyMatrix[1][1] * phraseToNum.get(i+1) + keyMatrix[1][2] * phraseToNum.get(i+2)) % 26;
            int z = (keyMatrix[2][0] * phraseToNum.get(i) + keyMatrix[2][1] * phraseToNum.get(i+1) + keyMatrix[2][2] * phraseToNum.get(i+2)) % 26;
            phraseEncoded.add(x == 0 ? 26 : x );
            phraseEncoded.add(y == 0 ? 26 : y );
            phraseEncoded.add(z == 0 ? 26 : z );
        }
        resultado(phraseEncoded);
    }

    /**
     * Descifra el texto con una matriz 3x3.
     * @param texto el texto a descifrar.
     */
    public static void decryptx3(String texto)
    {
        int[][] keyMatrix, revKeyMatrix;
        ArrayList<Integer> phraseToNum = new ArrayList<>();
        ArrayList<Integer> phraseDecoded = new ArrayList<>();

        texto = texto.toUpperCase();
        keyMatrix = getclaveMatrix3();

        for(int k=0; k < texto.length(); k++)
        {
            if (texto.charAt(k) == '¤' || texto.charAt(k) == '¥')
                phraseToNum.add(14);
            else
            {
            switch (texto.charAt(k))
            {
                case 'A': phraseToNum.add(0); break;
                case 'B': phraseToNum.add(1); break;
                case 'C': phraseToNum.add(2); break;
                case 'D': phraseToNum.add(3); break;
                case 'E': phraseToNum.add(4); break;
                case 'F': phraseToNum.add(5); break;
                case 'G': phraseToNum.add(6); break;
                case 'H': phraseToNum.add(7); break;
                case 'I': phraseToNum.add(8); break;
                case 'J': phraseToNum.add(9); break;
                case 'K': phraseToNum.add(10); break;
                case 'L': phraseToNum.add(11); break;
                case 'M': phraseToNum.add(12); break;
                case 'N': phraseToNum.add(13); break;
                case 'O': phraseToNum.add(15); break;
                case 'P': phraseToNum.add(16); break;
                case 'Q': phraseToNum.add(17); break;
                case 'R': phraseToNum.add(18); break;
                case 'S': phraseToNum.add(19); break;
                case 'T': phraseToNum.add(20); break;
                case 'U': phraseToNum.add(21); break;
                case 'V': phraseToNum.add(22); break;
                case 'W': phraseToNum.add(23); break;
                case 'X': phraseToNum.add(24); break;
                case 'Y': phraseToNum.add(25); break;
                case 'Z': phraseToNum.add(26); break;
            }
            }
        }

        revKeyMatrix = matrizInversa(keyMatrix);

        for(int i=0; i < phraseToNum.size(); i += 3)
        {
            phraseDecoded.add((revKeyMatrix[0][0] * phraseToNum.get(i) + revKeyMatrix[0][1] * phraseToNum.get(i+1) + revKeyMatrix[0][2] * phraseToNum.get(i+2)) % 26);
            phraseDecoded.add((revKeyMatrix[1][0] * phraseToNum.get(i) + revKeyMatrix[1][1] * phraseToNum.get(i+1) + revKeyMatrix[1][2] * phraseToNum.get(i+2)) % 26);
            phraseDecoded.add((revKeyMatrix[2][0] * phraseToNum.get(i) + revKeyMatrix[2][1] * phraseToNum.get(i+1) + revKeyMatrix[2][2] * phraseToNum.get(i+2)) % 26);
        }
        resultado(phraseDecoded);
    }

    public static void main(String[] args)
    {
        String opt, phrase;
        Scanner keyboard = new Scanner(System.in,"ISO-8859-1");
        System.out.println("\t\t\t*** Proyecto 1 PARTE 2 ***\n\t\t\t\t    HILL\n1.Cifrar con matriz 2x2\n2.Descifrar con matriz 2x2\n3.Cifrar con matriz 3x3\n4.Descifrar con matriz 3x3");
        opt = keyboard.nextLine();
        switch (opt)
        {
            case "1":
                System.out.print("Enter phrase to encrypt: ");
                phrase = keyboard.nextLine();
                encrypt(phrase);
                break;

            case "2":
                System.out.print("Enter phrase to decrypt: ");
                phrase = keyboard.nextLine();
                decrypt(phrase);
                break;

            case "3":
                System.out.print("Enter phrase to decrypt3x3: ");
                phrase = keyboard.nextLine();
                encrypt3(phrase);
                break;

            case "4":
                System.out.print("Enter phrase to decrypt3x3: ");
                phrase = keyboard.nextLine();
                decryptx3(phrase);
                break;
        }
    }
}   
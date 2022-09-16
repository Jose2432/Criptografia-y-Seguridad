import java.util.Scanner;

public class Vigenere{

  /**
   * Método para cifrar un mensaje
   * @param texto El mensaje que se va a cifrar
   * @param clave La clave con la que se cifrara el mensaje
   * @return El mensaje cifrado
   */
  static String cifrar(String texto, String clave){
      String textoCifrado = "";
      for(int i=0; i < texto.length(); i++){
        // converting in range 0-25
        int x = (texto.charAt(i) + clave.charAt(i)) % 26;
        // convert into alphabets(ASCII)
        x += 'A';
        textoCifrado += (char)(x);
      }
      return textoCifrado;
  }

  /**
   * Método para descifrar un mensaje
   * @param textoCifrado El mensaje que se va a descifrar
   * @param clave La clave con la que se descifrara el mensaje
   * @return El mensaje original
   */
  static String descifrar(String textoCifrado, String clave){
      String textoOriginal = "";
      for (int i=0; i < textoCifrado.length() && i < clave.length(); i++){
        // converting in range 0-25
        int x = (textoCifrado.charAt(i) - clave.charAt(i) + 26) % 26;
        // convert into alphabets(ASCII)
        x += 'A';
        textoOriginal += (char)(x);
      }
      return textoOriginal;
  }

  /**
   * Método para generar la clave de forma ciclica hasta que la longitud sea diferente a la del mensaje original
   * @param texto El mensaje cifrado o descifrado
   * @param clave La clave
   * @return La clave para cifrar o descifrar
   */
  static String llave(String texto, String clave){
      int x = texto.length();
      for(int i=0; ; i++){
        if (x == i){
          i = 0;
        }
        if(clave.length() == texto.length()){
          break;
        }
        clave += (clave.charAt(i));
      }
      return clave;
  }

  /**
   * Método para evitar errores cuando el usuario ingrese la opcion en el menu
   */
  public static int daEleccion(){
      boolean error = false;
      int eleccion = 0;
      Scanner en = new Scanner(System.in);
      try {
        eleccion = en.nextInt();
      }catch (Exception e) {
        System.out.println("Dato introducido no correcto.");
        error = true;
      }
      finally{
        if(error == true){
          return  daEleccion();
        }else{
          return eleccion;
        }
      }
  }

  /**
   * Método para quitar numeros y simbolos de una cadena
   * @param cadena La cadena para quitar simbolos
   * @return La cadena sin simbolos
   */
  public static String soloCadena(String cadena){
      cadena = cadena.replaceAll("[\\d (?<=[ (])@|@(?=[.,;?:) !])]", "");
      cadena.trim();
      return cadena;
  }

  public static void main(String[] args){

    int opcion = 0;
    Scanner in = new Scanner(System.in);
    System.out.println("\n|||||||||||||||   B I E N V E N I D O   |||||||||||||||");

    do {
      System.out.println();
      System.out.println("Bienvenido \n¿Qué deseas hacer?");
      System.out.println("1. Cifrar un mensaje.\n2. Descifrar un mensaje.\n3. Salir.");
      opcion = daEleccion();
      System.out.println();
      switch (opcion) {
        case 1:
            System.out.println("CIFRAR MENSAJE.\nIntroduce el mensaje que quieres cifrar (solo letras).");
            String mensaje = in.nextLine();
            mensaje = soloCadena(mensaje);
            System.out.println("Ingresa la clave para cifrar el mensaje (solo letras).");
            String clave = in.nextLine();
            clave = soloCadena(clave);
            mensaje = mensaje.toUpperCase();
            clave = clave.toUpperCase();
            System.out.println("\nMensaje original: " + mensaje);
            System.out.println("Clave de cifrado: " + clave);
            System.out.println("Mensaje cifrado: " + cifrar(mensaje,llave(mensaje,clave)) + "\n");

        break;

        case 2:
            System.out.println("DESCIFRAR MENSAJE.\nIntroduce el mensaje cifrado (solo letras).");
            String mensajeCifrado = in.nextLine();
            System.out.println("Ingresa la clave para descifrar el mensaje (solo letras).");
            String claveDescifrar = in.nextLine();
            mensajeCifrado = soloCadena(mensajeCifrado);
            claveDescifrar = soloCadena(claveDescifrar);
            mensajeCifrado = mensajeCifrado.toUpperCase();
            claveDescifrar = claveDescifrar.toUpperCase();
            System.out.println("\nTexto cifrado: " + mensajeCifrado);
            System.out.println("Clave de cifrado: " + claveDescifrar);
            System.out.println("Palabra original: " + descifrar(mensajeCifrado, llave(mensajeCifrado,claveDescifrar)) + "\n");

        break;

        case 3:
            System.out.println("Hasta la proxima :D");
            System.exit(1);
        break;

        default:
            System.out.println("Respuesta inválida, intenta nuevamente.");
      }
    } while (opcion != 3);
  }
}

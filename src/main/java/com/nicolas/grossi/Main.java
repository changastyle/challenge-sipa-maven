package com.nicolas.grossi;

import java.util.Arrays;
import java.util.List;

public class Main {
  final int MAX_PLATE_LENGTH = 4;
  final static List<String> arrGlobal =
      Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

  public static void main(String args[]) {
    System.out.println(generatePlate("0000"));
  }
  public static String generatePlate(String input) {
    String rta = "";
    int largoInput = input.length();

    if (isNumeric(input)) {
      CharCollapsoDTO result = aumentar_devuelve_si_collapsa(input);
      if (result != null) {
        if (result.colapso) {
          rta = quitar_collapso(quitar_collapso(result.getRta())) + "A";
        } else {
          rta = result.getRta();
        }
      }
    } else {
      CharCollapsoDTO partido_a_la_mitad_si_contiene_chars = partir_mitad_devuelve_si_collapsa(input);

      rta = partido_a_la_mitad_si_contiene_chars.getRta();
    }

//    int aux = stringToInt(rta);
    String rtaAutocomplete = autoCompletarZeros(rta,input.length());

    return rtaAutocomplete;
  }


  public static CharCollapsoDTO partir_mitad_devuelve_si_collapsa(String input) {
    CharCollapsoDTO result = new CharCollapsoDTO();
    String rta = "";
    boolean colapsa = false;

    // SI TENGO CARACTERES DE LETRA:
    int posPrimeraLetra = firstCharacterPosition(input);
    String primeraLetra = String.valueOf(input.charAt(posPrimeraLetra));
    int posPrimeraLetraGlobal = arrGlobal.indexOf(primeraLetra);

    //DIVIDO A LA MITAD POR LA POSICION DE LA LETRA:
    String primeraParte = input.substring(0, posPrimeraLetra);
    String segundaParte = input.substring(posPrimeraLetra + 1, input.length());

    if (segundaParte.length() > 0) {
      // COMIENZO POR LA SEGUNDA PARTE A VER SI COLLAPSA:
      CharCollapsoDTO resAumentarSegundaParte = aumentar_devuelve_si_collapsa(segundaParte);
      if (resAumentarSegundaParte != null) {
        if (resAumentarSegundaParte.isColapso()) {
          int posProximaLetra = posPrimeraLetra + 1;
          String proximaLetra = arrGlobal.get(posProximaLetra);
          rta = primeraParte + "" + proximaLetra + "" + resAumentarSegundaParte.getRta();
        }
        else
        {
          if(esTodos9s(primeraParte) && (esTodos9s(segundaParte)|| !isNumeric(segundaParte)))
          {
            primeraParte = autoCompletarZeros("0", primeraParte.length());
          }
          rta = primeraParte + "" + primeraLetra + "" + resAumentarSegundaParte.getRta();
        }
      }
    } else if (primeraParte.length() > 0) {
      // SI LA PRIMERA PARTE ESTA COLLAPSADA VOY POR LA PRIMERA:
      CharCollapsoDTO resAumentarPrimeraParte = aumentar_devuelve_si_collapsa(primeraParte);
      if (resAumentarPrimeraParte != null) {
        if (resAumentarPrimeraParte.isColapso()) {
          int posProximaLetra = posPrimeraLetraGlobal + 1;
          String proximaLetra = arrGlobal.get(posProximaLetra);
          primeraParte = resAumentarPrimeraParte.getRta();
          String withouCollapsePrimeraParte = quitar_collapso(primeraParte);
          rta = withouCollapsePrimeraParte + "" + proximaLetra + "" + segundaParte;
        }
      }
    }
    else {
      rta= arrGlobal.get(posPrimeraLetraGlobal+1);
      colapsa = false;
      System.out.println("??  creo que tengo que aumentar la letra y fue..");
    }



    result.setRta(rta);
    result.setColapso(colapsa);
    return result;
  }
  public static CharCollapsoDTO aumentar_devuelve_si_collapsa(String input) {
    CharCollapsoDTO result = new CharCollapsoDTO();
    String rta = "";
    boolean collapsa = false;

    if (esTodos9s(input)) {
      // SI TENGO TODOS 9 AUMENTO EL NUMERO , COLLAPSO Y RESUELVE EL DE ARRIBA:
      int inputINT = stringToInt(input);
      inputINT++;
      rta = String.valueOf(inputINT);
      collapsa = true;
    } else {
      // SI NO TENGO TODOS 9 TENGO QUE VER SI TENGO UN CARACTER SI TENGO CARACTER VUELVO A DIVIDIR Y HACER TODA LA MOVIDA DE DERECHA Y ETC:
      if(isNumeric(input))
      {
        // LO QUE QUEDA ES TODO NUMERO:
        int inputINT = stringToInt(input);
        inputINT++;
        rta = String.valueOf(inputINT);
      }
      else
      {
        // LO QUE QUEDA HAY LETRAS Y NUMEROS:
        CharCollapsoDTO c = partir_mitad_devuelve_si_collapsa(input);
        rta = c.getRta();
      }
    }
      result.setRta(rta);
      result.setColapso(collapsa);
    return result;
  }
  public static String quitar_collapso(String input) {
    String rta = input;
    rta = input.substring(1, input.length());
    return rta;
  }





  public static boolean esTodos9s(String input) {
    if (input == null || input.isEmpty()) {
      return false;
    }

    // Verifica si todos los caracteres son '9'
    for (char c : input.toCharArray()) {
      if (c != '9') {
        return false;
      }
    }

    return true;
  }

  public static boolean esTodosZs(String input) {
    if (input == null || input.isEmpty()) {
      return false;
    }

    // Verifica si todos los caracteres son 'Z'
    for (char c : input.toCharArray()) {
      if (c != 'Z') {
        return false;
      }
    }

    return true;
  }

  public static String aumentar_simple(String charAnt) {
    String rta = "0";

    if (charAnt.equalsIgnoreCase("9")) {
      rta = "10";
    }
    if (rta == "0") {
      int posCharAnteriorArrGlogal = arrGlobal.indexOf(charAnt);
      int nvaPosGlobal = posCharAnteriorArrGlogal + 1;

      if (nvaPosGlobal < arrGlobal.size()) {
        String nuevaLetra = arrGlobal.get(nvaPosGlobal);
        rta = nuevaLetra;
      }
    }


    return rta;
  }

  public static String reemplazarNuevePorCeroDesdePos(String input, int pos) {
    if (pos < 0 || pos >= input.length()) {
      throw new IndexOutOfBoundsException("La posición está fuera del rango del input.");
    }

    // Dividimos el string en dos partes: izquierda y derecha según la posición indicada
    String izquierda = input.substring(0, pos);
    String derecha = input.substring(pos);

    // Reemplazamos todos los '9' por '0' solo en la parte derecha
    derecha = derecha.replace('9', '0');

    // Concatenamos la parte izquierda sin cambios y la parte derecha modificada
    return izquierda + derecha;
  }

  public static boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  public static String replaceLast(String original, String target, String replacement) {
    if (target.isEmpty()) {
      throw new IllegalArgumentException("El carácter objetivo no puede estar vacío.");
    }

    int lastIndex = original.lastIndexOf(target);

    if (lastIndex == -1) {
      return original;
    }

    return original.substring(0, lastIndex) + replacement + original.substring(lastIndex + target.length());
  }

  public static String reemplazarCaracter(String original, int pos, String nuevo) {
    if (nuevo.length() != 1) {
      throw new IllegalArgumentException("El nuevo carácter debe ser una cadena de longitud 1.");
    }
    if (pos < 0 || pos >= original.length()) {
      throw new IndexOutOfBoundsException("La posición está fuera del rango del string original.");
    }

    StringBuilder resultado = new StringBuilder(original);
    resultado.setCharAt(pos, nuevo.charAt(0));

    return resultado.toString();
  }


  //  public static String reemplazarNuevePorCero(String input , int pos) {
  //    String rta = input.replace('9', '0');
  //    return  rta;
  //  }

  //public static String reemplazarNuevePorCero(String input, int pos) {
  //  // Si pos es -1, reemplazamos todos los '9' por '0'
  //  if (pos == -1) {
  //    return input.replace('9', '0');
  //  }
  //
  //  // Validamos que la posición esté dentro del rango
  //  if (pos < 0 || pos >= input.length()) {
  //    throw new IndexOutOfBoundsException("La posición está fuera del rango del input.");
  //  }
  //
  //  // Verificamos si en la posición indicada hay un '9'
  //  if (input.charAt(pos) == '9') {
  //    // Si es un '9', creamos un StringBuilder y lo reemplazamos por '0'
  //    StringBuilder rta = new StringBuilder(input);
  //    rta.setCharAt(pos, '0');
  //    return rta.toString();
  //  }
  //
  //  // Si no hay un '9' en esa posición, devolvemos el string original
  //  return input;
  //}

  //  public static String reemplazarNuevePorCero(String input) {
  //    String rta = input.replace('9', '0');
  //    return  rta;
  //  }
  public static String reemplazarZPorA(String input) {
    return input.replace('Z', 'A');
  }

  public static int stringToInt(String input) {
    int rta = 0;
    try {
      rta = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      System.out.println("Error: La cadena no es un número válido.");
      rta = 0;
    }
    return rta;
  }

  public static String autoCompletarZeros(String numero, int largoEsperado) {
    String rta = String.valueOf(numero);

    while (rta.length() < largoEsperado) {
      rta = "0" + rta;
    }

    return rta;
  }
  public static String autoCompletarZeros(int numero, int largoEsperado) {
    String rta = String.valueOf(numero);

    while (rta.length() < largoEsperado) {
      rta = "0" + rta;
    }

    return rta;
  }

  public static void dameCantChars(String input) {
    int digitCount = 0;
    int charCount = 0;

    for (char c : input.toCharArray()) {
      if (Character.isDigit(c)) {
        digitCount++;
      } else if (Character.isLetter(c)) {
        charCount++;
      }
    }

    System.out.println("Cantidad de dígitos: " + digitCount);
    System.out.println("Cantidad de caracteres: " + charCount);
  }

  public static String extraerParteNumerica(String input) {
    return input.replaceAll("[^0-9]", "");
  }

  public static int firstCharacterPosition(String input) {
    int rta = input.length();
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (Character.isLetter(c)) {
        rta = i;
        break;
      }
    }

    return rta;
  }

  public static int generateMaxNumber(int digits) {
    int rta = 0;
    if (digits <= 0) {
      rta = 0;
    }
    else
    {
      StringBuilder maxNumber = new StringBuilder();

      for (int i = 0; i < digits; i++) {
        maxNumber.append('9');
      }
      rta = Integer.parseInt(maxNumber.toString());
    }



    return rta;
  }
}

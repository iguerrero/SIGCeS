package edu.grupo9.sigces;

import java.util.Scanner;

public class Utilidades {

    /* M�TODOS GEN�RICOS */

    /**
     * Borra la pantalla para mejorar la visualizaci�n.
     */
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Selecci�n de opciones num�ricas. Para usar antes de Switch en los Menu.
     * @return int seleccion
     */
    public static int seleccion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elija una opci\u00F3n: ");
        int elige = scanner.nextInt();
        scanner.nextLine();
        return elige;
    }

    /**
     * Retardo de tiempo. Pa meterle drama a la cosa...
     * @param milisegundos
     */
    public static void dormirPor(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * En este caso, valida que la clave sea num�rica y de 4 d�gitos.
     * Se puso aparte para poder modificar el m�todo de validaci�n sin
     * tener que tocar el c�digo.
     * @param clave
     * @return boolean
     */
    public static boolean validarClave(String clave) {
        return clave.length() == 4;
    }


}

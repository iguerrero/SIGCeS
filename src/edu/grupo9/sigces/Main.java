/**
 *  El presente programa es un prototipo y es parte de los trabajos prácticos
 *  de la cátedra de <b>Programación Orientada a Objetos</b> de la carrera de Lic. en Informática
 *  de la <b>Universidad Empresarial Siglo XXI</b>.
 * @author Ignacio Guerrero
 * @version 0.1 TP2
 * @date 27/04/2022
 * Puede verse online en <a href="https://github.com/iguerrero21/SIGCeS">Github</a>.
 */
package edu.grupo9.sigces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    final static String fileMedicos = "medicos.csv";
    final static String fileAdmins = "admins.csv";

    /**
     * Borra la pantalla para mejorar la visualización.
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pantalla de Bienvenida.
     */
    public static void bienvenida() {

        clearScreen();
        System.out.println("""
                Bienvenido a\s
                        ____ ___ ____  ____     ____\s
                       / ___|_ _/ ___|/ ___|___/ ___|\s
                       \\___ \\| | |  _| |   / _ \\___ \\\s
                        ___) | | |_| | |__|  __/___) |\s
                       |____/___\\____|\\____\\___|____/\s
                Sistema Integral de Gestión de Centros de Salud""");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Selecciona entre usuario Médico y usuario Admin.
     */
    public static void menuInicio() {
        try{
            clearScreen();
            System.out.println("""
                    \u00BFEs usted M\u00E9dico o Administrador?\s
                    1. M\u00E9dico\s
                    2. Administrador""");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Elija una opci\u00F3n: ");
            int seleccion = scanner.nextInt();
            switch (seleccion) {
                case 1 -> {
                    Medico usuarioMed = buscarMedico();
                    menuMedico(usuarioMed);
                }
                case 2 -> {
                    Admin usuarioAdmin = buscarAdmin();
                    menuAdmin(usuarioAdmin);
                }
                default -> {
                    System.out.println("Opci\u00F3n no encontrada. Por favor, intente nuevamente.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    clearScreen();
                    menuInicio();
                }
            }
        } catch (Exception e) {
            /* Esta excepción es en caso de que el usuario ingrese
             * una letra o un símbolo, en lugar de un entero.
             */
            System.out.println("Los datos no fueron encontrados. Por favor, intente nuevamente.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            clearScreen();
            menuInicio();
        }
    }

    /**
     * Login. Solicita el DNI y la clave para loguearse a la aplicación.
     * En la presente versión no hay usuario, y la clave es numérica de 4 dígitos.
     * Tampoco se realiza un hash sobre la clave, por lo cual es visible en la
     * planilla de cálculo CSV.
     * @param file String. Es provisto por el método que solicita este procedimiento.
     */
    public static ArrayList<String> login(String file) {
        clearScreen();
        System.out.println("Para acceder a la aplicaci\u00F3n, deber\u00E1 proveer su DNI y su clave de acceso.");
        try {
            int numDigitos = 0;
            int dni = 0;
            int clave = 0;

            while (numDigitos != 8) { //Corrobora que sean 8 dígitos.
                System.out.print("Por favor, ingrese los 8 n\u00FAmeros de su DNI para ingresar: ");
                Scanner scanner = new Scanner(System.in);
                dni = scanner.nextInt();
                numDigitos = String.valueOf(dni).length();
                if (numDigitos != 8) {
                    System.out.println("Su DNI debe tener 8 d\u00EDgitos.");
                }
            }

            while (numDigitos != 4) { //Corrobora que sean 4 dígitos.
                System.out.print("Ahora, ingrese los 4 n\u00FAmeros de su Clave: ");
                Scanner scanner = new Scanner(System.in);
                clave = scanner.nextInt();
                numDigitos = String.valueOf(clave).length();
                if (numDigitos != 4) {
                    System.out.println("Su Clave debe tener 4 d\u00EDgitos. Inténtelo nuevamente.");
                }
            }

            return leerCSV(dni, clave, file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Abre el archivo .CSV correspondiente a file y busca los datos coincidentes con dni y clave.
     * @param dni int
     * @param clave int
     * @param file String
     * @return ArrayList values or null.
     * @throws IOException Archivo no encontrado.
     */
    public static ArrayList<String> leerCSV(int dni, int clave, String file) throws IOException {
        // Abre file y lee cada línea.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            // Lee línea por línea
            while ((s = br.readLine()) != null) {
                // Divide cada línea y la divide en cada punto y coma.
                String[] values = s.split(";");
                int dniGuardado = Integer.parseInt(values[3]);
                int claveGuardada = Integer.parseInt(values[4]);
                if (Objects.equals(dni, dniGuardado) && Objects.equals(clave, claveGuardada)) {
                    return new ArrayList<>(Arrays.asList(values));
                }
            }
            throw new IOException("Archivo no encontrado.");
        }
    }

    /**
     * Busca al médico cuyo DNI y clave coincidan con los del archivo <b>medicos.csv<b/>.
     * @return nuevo Medico.
     */
    public static Medico buscarMedico() {
        ArrayList<String> datosMedico = login(fileMedicos);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return new Medico(datosMedico.get(1),
                datosMedico.get(2),
                Integer.parseInt(datosMedico.get(3)),
                Integer.parseInt(datosMedico.get(4)),
                datosMedico.get(5),
                datosMedico.get(6),
                datosMedico.get(7),
                LocalDate.parse(datosMedico.get(8), formatter),
                datosMedico.get(9).charAt(0),
                datosMedico.get(10),
                datosMedico.get(11),
                datosMedico.get(12)
        );
    }

    public static void menuMedico(Medico medico) {
        clearScreen();
        String tratamiento = String.valueOf(medico.getSexo()).equals("M")? "Bienvenido, Dr. ": "Bienvenida, Dra. ";
        System.out.println(tratamiento + medico.getApellido() + "\n" +
                           "Seleccione una de las siguientes opciones: \n" +
                           "1. Ver mi agenda \n" +
                           "2. Ver Historia Cl\u00EDnica");
    }

    public static Admin buscarAdmin() {
        ArrayList<String> datosAdmin = login(fileAdmins);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return new Admin(datosAdmin.get(1),
                datosAdmin.get(2),
                Integer.parseInt(datosAdmin.get(3)),
                Integer.parseInt(datosAdmin.get(4)),
                datosAdmin.get(5),
                datosAdmin.get(6),
                datosAdmin.get(7),
                LocalDate.parse(datosAdmin.get(8), formatter),
                datosAdmin.get(9).charAt(0),
                datosAdmin.get(10),
                datosAdmin.get(11),
                datosAdmin.get(12)
        );
    }

    private static void menuAdmin(Admin admin) {
        clearScreen();
        String tratamiento = String.valueOf(admin.getSexo()).equals("M")? "Bienvenido, ": "Bienvenida, ";
        System.out.println(tratamiento + admin.getNombre() + "\n" +
                "Seleccione una de las siguientes opciones: \n" +
                "1. Gestionar Turnos \n" +
                "2. Ver Historia Cl\u00EDnica" +
                "3. Gestionar M\u00E9dicos" +
                "4. Gestionar Administradores");
    }

    public static void main(String[] args) {
        bienvenida();
        menuInicio();


    }
}

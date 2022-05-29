/*  El presente programa es un prototipo y es parte de los trabajos prácticos
 *  de la cátedra de <b>Programación Orientada a Objetos</b> de la carrera de Lic. en Informática
 *  de la <b>Universidad Empresarial Siglo XXI</b>.
 * Puede verse online en <a href="https://github.com/iguerrero21/SIGCeS">Github</a>
 */
package edu.grupo9.sigces;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static edu.grupo9.sigces.Utilidades.*;


/**
 * @author Ignacio Guerrero
 * @version 0.1 TP2
 * @Date: 27/04/2022 <br>
 * Puede verse online en <a href="https://github.com/iguerrero21/SIGCeS">Github</a>.
 */
public class Main implements VarsGlobales {


    /**
     * Pantalla de Bienvenida.
     */
    public static void bienvenida() {

        limpiarPantalla();
        System.out.println("""
                Bienvenido a\s
                        ____ ___ ____  ____     ____\s
                       / ___|_ _/ ___|/ ___|___/ ___|\s
                       \\___ \\| | |  _| |   / _ \\___ \\\s
                        ___) | | |_| | |__|  __/___) |\s
                       |____/___\\____|\\____\\___|____/\s
                Sistema Integral de Gestión de Centros de Salud""");
        dormirPor(3000);
        limpiarPantalla();
        SQLiteDB.crearTablas(SQLiteDB.conectar());
    }

    /**
     * Selecciona entre usuario Médico y usuario Admin.
     */
    public static void menuInicio() {
        try{
            System.out.println("""
                    \u00BFEs usted M\u00E9dico o Administrador?\s
                    1. M\u00E9dico\s
                    2. Administrador""");
            switch (seleccion()) {
                case 1 -> menuMedico(buscarMedico());
                case 2 -> Admin.menuAdmin(Admin.buscarAdmin());
                default -> {
                    System.out.println("Por favor, elija una n\u00FAmero dentro de las opciones provistas.");
                    dormirPor(1000);
                    limpiarPantalla();
                    menuInicio();
                }
            }
        } catch (Exception e) {
            /* Esta excepción es en caso de que el usuario ingrese
             * una letra o un símbolo, en lugar de un entero.
             */
            System.out.println("Los datos no fueron encontrados. Por favor, intente nuevamente.");
            dormirPor(1000);
            limpiarPantalla();
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
        limpiarPantalla();
        System.out.println("Para acceder a la aplicaci\u00F3n, deber\u00E1 proveer su DNI y su clave de acceso.");
        try {
            int numDigitos = 0;
            int dni = 0;

            while (numDigitos != 8) { //Corrobora que sean 8 dígitos.
                System.out.print("Por favor, ingrese los 8 n\u00FAmeros de su DNI para ingresar: ");
                Scanner scanner = new Scanner(System.in);
                dni = scanner.nextInt();
//                scanner.close();
                numDigitos = String.valueOf(dni).length();
                if (numDigitos != 8) {
                    System.out.println("Su DNI debe tener 8 d\u00EDgitos.");
                }
            }

            String clave = null;
            while (numDigitos != 4) { //Corrobora que sean 4 dígitos.
                System.out.print("Ahora, ingrese los 4 n\u00FAmeros de su Clave: ");
                Scanner scanner = new Scanner(System.in);
                clave = scanner.nextLine();
//                scanner.close();
                numDigitos = clave.length();
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
     * Abre el archivo .CSV correspondiente a planilla y busca los datos coincidentes con dni y clave.
     * @param dni int
     * @param clave int
     * @param planilla String
     * @return ArrayList values or null.
     * @throws IOException Archivo no encontrado.
     */
    public static ArrayList<String> leerCSV(int dni, String clave, String planilla) throws IOException {
        // Abre planilla y lee cada línea.
        try (BufferedReader br = new BufferedReader(new FileReader(planilla))) {
            String s;
//            br.close();
            // Lee línea por línea
            while ((s = br.readLine()) != null) {
                // Divide cada línea y la divide en cada punto y coma.
                String[] values = s.split(";");
                int dniGuardado = Integer.parseInt(values[3]);
                String claveGuardada = values[4];
                if (Objects.equals(dni, dniGuardado) && Objects.equals(clave, claveGuardada)) {
                    return new ArrayList<>(Arrays.asList(values));
                }
            }
            throw new IOException("Archivo no encontrado.");
        }
    }

    /**
     * Busca al médico cuyo DNI y clave coincidan con los del archivo <b>medicos.csv<b/>.
     * TODO Este método debe migrarse a la clase Medico
     * @return nuevo Medico.
     */
    public static Medico buscarMedico() {
        ArrayList<String> datosMedico = login(planillaMedicos);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return new Medico(datosMedico.get(1),
                datosMedico.get(2),
                Integer.parseInt(datosMedico.get(3)),
                datosMedico.get(4),
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

    /**
     * TODO Este método debe migrarse a la clase Medico
     * @param medico
     */

    public static void menuMedico(@NotNull Medico medico) {
        limpiarPantalla();
        String tratamiento = String.valueOf(medico.obtenerSexo()).equals("M")? "Bienvenido, Dr. ": "Bienvenida, Dra. ";
        System.out.println(tratamiento + medico.obtenerApellido() + "\n" +
                           "Seleccione una de las siguientes opciones: \n" +
                           "1. Ver mi agenda semanal \n" +
                           "2. Ver Historia Cl\u00EDnica");
    }
    
    /**
     * Busca al administrador cuyo DNI y clave coincidan con los del archivo <b>admin.csv<b/>.
     * @return nuevo Admin.
     */

    public static void main(String[] args) {
        bienvenida();
        menuInicio();

    }
}

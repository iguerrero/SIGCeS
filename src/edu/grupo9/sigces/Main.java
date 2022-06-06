/*  El presente programa es un prototipo y es parte de los trabajos prácticos
 *  de la cátedra de <b>Programación Orientada a Objetos</b> de la carrera de Lic. en Informática
 *  de la <b>Universidad Empresarial Siglo XXI</b>.
 * Puede verse online en <a href="https://github.com/iguerrero21/SIGCeS">Github</a>
 */
package edu.grupo9.sigces;

import edu.grupo9.sigces.dao.AdminDao;
import edu.grupo9.sigces.dao.AdminDaoImpl;
import edu.grupo9.sigces.dao.MedicoDao;
import edu.grupo9.sigces.dao.MedicoDaoImpl;
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

import static edu.grupo9.sigces.SQLiteDB.conectar;
import static edu.grupo9.sigces.Utilidades.*;


/**
 * @author Ignacio Guerrero
 * @version 0.1 TP2
 * @Date: 27/04/2022 <br>
 * Puede verse online en <a href="https://github.com/iguerrero21/SIGCeS">Github</a>.
 */
public class Main implements VarsGlobales {

    private static Sesion sesion;

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
//        conectar();
//        SQLiteDB.crearTablas();
//        SQLiteDB.agregarDatos();
    }

    /**
     * Selecciona entre usuario Médico y usuario Admin.
     */
    public static void menuInicio(Sesion sesion) {
        try{
            System.out.println("""
                    \u00BFEs usted M\u00E9dico o Administrador?\s
                    1. M\u00E9dico\s
                    2. Administrador""");
            switch (seleccion()) {
                case 1: {
                    MedicoDao med = new MedicoDaoImpl();
                    sesion = med.loginMedico(0);
                    menuMedico(sesion);
                }
                case 2: {
                    AdminDao adm = new AdminDaoImpl();
                    sesion = adm.loginAdmin(0);
                    menuAdmin(sesion);
                }
                default: {
                    System.out.println("Por favor, elija una n\u00FAmero dentro de las opciones provistas.");
                    dormirPor(1000);
                    limpiarPantalla();
                    menuInicio(sesion);
                }
            }
        } catch (Exception e) {
            /* Esta excepción es en caso de que el usuario ingrese
             * una letra o un símbolo, en lugar de un entero.
             */
            System.out.println("Los datos no fueron encontrados. Por favor, intente nuevamente.");
            System.err.println(e.getMessage());
            dormirPor(1000);
            limpiarPantalla();
            if (sesion != null && sesion.obtenerRol().equals("Médico")) {
                menuMedico(sesion);
            } else if (sesion != null && sesion.obtenerRol().equals("Admin")) {
                menuAdmin(sesion);
            } else {
                menuInicio(sesion);
            }
        }
    }

    public static void menuAdmin(Sesion sesion) {
        limpiarPantalla();
        String tratamiento = String.valueOf(sesion.obtenerSexo()).equals("M") ? "Bienvenido, " : "Bienvenida, ";
        System.out.println(tratamiento + sesion.obtenerNombre() + "\n" +
                "Selecciona una de las siguientes opciones: \n" +
                "\033[31m1. Gestionar Turnos  \033[0m \n" +
                "\033[31m2. Ver Historia Cl\u00EDnica \033[0m \n" +
                "3. Gestionar M\u00E9dicos \n" +
                "4. Gestionar Administradores ");
        switch (seleccion()) {
            case 1 -> gestionarTurnos();
            case 2 -> gestionarPacientes();
            case 3 -> gestionarMedicos(sesion);
            case 4 -> gestionarAdmins(sesion);
        }
    }

    public static void gestionarMedicos(Sesion sesion) {
        Medico medico = null;
        System.out.println("""
                ¿Qué desea hacer? \s
                1. Cargar un nuevo Médico \s
                2. Modificar Datos \s
                3. Eliminar Médico \s
                \033[31m4. Gestionar Agenda\033[0m \s
                5. Volver al menú anterior \s
                """);

        MedicoDao med = new MedicoDaoImpl();
        switch (seleccion()) {
            case 1 -> { med.cargarNuevo(); gestionarMedicos(sesion); }
            case 2 -> modificarMedico(sesion);
            case 3 -> eliminarMedico(sesion);
            case 5 -> menuAdmin(sesion);
        }
    }

    public static void modificarMedico(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s 
                el identificador de médico que desea modificar.\s
                1. Nombre y Apellido \s 
                2. Identificador \s
                3. Volver al menú anterior
                """);
        Scanner scanner = new Scanner(System.in);
        MedicoDao med = new MedicoDaoImpl();
        Medico medico = null;
        switch (seleccion()) {
            case 1: {
                System.out.println("Ingrese el nombre y apellido del Médico");
                Scanner sc = new Scanner(System.in);
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido: ");
                String apellido = sc.nextLine();
                ArrayList<Medico> medicos = med.buscarMedicosPorNombre(nombre, apellido);
                if (medicos.size() == 1) {
                    medico = med.buscarMedicoPorId(medicos.get(0).obtenerIdMedico());
                    med.modificar(medico);
                } else if (medicos.size() > 1) {
                    System.out.println("""
                            Se encontraron más de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Medico cadaMed : medicos) {
                        System.out.println(cadaMed.toString());
                    }
                    medico = med.buscarMedicoPorId(sc.nextInt());
                    med.modificar(medico);
                } else {
                    System.out.println("La persona no ha sido encontrada.");
                    dormirPor(2000);
                    gestionarMedicos(sesion);
                }
                sc.close();
            }
            case 2: {
                System.out.println("Ingrese el identificador del Médico");
                Scanner sc = new Scanner(System.in);
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.close();
                medico = med.buscarMedicoPorId(id);
                med.modificar(medico);
            }
            case 3: { gestionarMedicos(sesion); }
        }
    }

    public static void eliminarMedico(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s 
                el identificador de médico que desea eliminar.\s
                1. Nombre y Apellido \s 
                2. Identificador \s
                3. Volver al menú anterior
                """);
        Scanner scanner = new Scanner(System.in);
        MedicoDao med = new MedicoDaoImpl();
        Medico medico = null;
        switch (seleccion()) {
            case 1: {
                System.out.println("Ingrese el nombre y apellido del Médico");
                Scanner sc = new Scanner(System.in);
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido: ");
                String apellido = sc.nextLine();
                ArrayList<Medico> medicos = med.buscarMedicosPorNombre(nombre, apellido);
                if (medicos.size() == 1) {
                    System.out.println("Usted está por eliminar el registro de: ");
                    System.out.println(medicos.get(0).toString());
                    System.out.println("¿Desea continuar? (S/N)");
                    String acepta = sc.nextLine().toUpperCase();
                    if (acepta.equals("S")) {
                        med.borrar(med.buscarMedicoPorId(medicos.get(0).obtenerIdMedico()));
                        System.out.println("El registro ha sido eliminado.");
                    } else {
                        eliminarMedico(sesion);
                    }

                } else {
                    System.out.println("""
                            Se encontraron más de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Medico cadaMed : medicos) {
                        System.out.println(cadaMed.toString());
                    }
                    med.borrar(med.buscarMedicoPorId(sc.nextInt()));
                }
                sc.close();
            }
            case 2: {
                System.out.println("Ingrese el identificador del Médico");
                Scanner sc = new Scanner(System.in);
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.close();
                med.borrar(med.buscarMedicoPorId(id));
            }
            case 3: { gestionarMedicos(sesion); }
        }
    }


    /* GESTION de ADMINISTRADORES */
    public static void gestionarAdmins(Sesion sesion) {
        System.out.println("""
                ¿Qué desea hacer? \s
                1. Cargar un nuevo Administrador \s
                2. Modificar Datos \s
                3. Eliminar Administrador \s
                4. Volver al menú anterior \s
                """);

        AdminDao adm = new AdminDaoImpl();
        switch (seleccion()) {
            case 1 -> { adm.cargarNuevo(); gestionarAdmins(sesion); }
            case 2 -> modificarAdmin(sesion);
            case 3 -> eliminarAdmin(sesion);
            case 4 -> menuAdmin(sesion);
        }
    }

    public static void modificarAdmin(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s 
                el identificador de médico que desea modificar.\s
                1. Nombre y Apellido \s 
                2. Identificador \s
                3. Volver al menú anterior
                """);
        Scanner scanner = new Scanner(System.in);
        AdminDao adm = new AdminDaoImpl();
        Admin admin = null;
        switch (seleccion()) {
            case 1: {
                System.out.println("Ingrese el nombre y apellido del Administrador");
                Scanner sc = new Scanner(System.in);
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido: ");
                String apellido = sc.nextLine();
                ArrayList<Admin> admins = adm.buscarAdminsPorNombre(nombre, apellido);
                if (admins.size() == 1) {
                    admin = adm.buscarAdminPorId(admins.get(0).obtenerIdAdmin());
                    adm.modificar(admin);
                } else if (admins.size() > 1) {
                    System.out.println("""
                            Se encontraron más de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Admin cadaAdm : admins) {
                        System.out.println(cadaAdm.toString());
                    }
                    admin = adm.buscarAdminPorId(sc.nextInt());
                    adm.modificar(admin);
                } else {
                    System.out.println("La persona no ha sido encontrada.");
                    dormirPor(2000);
                    gestionarAdmins(sesion);
                }
                sc.close();
            }
            case 2: {
                System.out.println("Ingrese el identificador del Administrador");
                Scanner sc = new Scanner(System.in);
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.close();
                admin = adm.buscarAdminPorId(id);
                adm.modificar(admin);
            }
            case 3: { gestionarAdmins(sesion); }
        }
    }

    public static void eliminarAdmin(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s 
                el identificador del administrador que desea eliminar.\s
                1. Nombre y Apellido \s 
                2. Identificador \s
                3. Volver al menú anterior
                """);
        Scanner scanner = new Scanner(System.in);
        AdminDao adm = new AdminDaoImpl();
        Admin admin = null;
        switch (seleccion()) {
            case 1: {
                System.out.println("Ingrese el nombre y apellido del Administrador");
                Scanner sc = new Scanner(System.in);
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido: ");
                String apellido = sc.nextLine();
                ArrayList<Admin> admins = adm.buscarAdminsPorNombre(nombre, apellido);
                if (admins.size() == 1) {
                    System.out.println("Usted está por eliminar el registro de: ");
                    System.out.println(admins.get(0).toString());
                    System.out.println("¿Desea continuar? (S/N)");
                    String acepta = sc.nextLine().toUpperCase();
                    if (acepta.equals("S")) {
                        adm.borrar(adm.buscarAdminPorId(admins.get(0).obtenerIdAdmin()));
                        System.out.println("El registro ha sido eliminado.");
                    } else {
                        eliminarAdmin(sesion);
                    }

                } else {
                    System.out.println("""
                            Se encontraron más de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Admin cadaAdm : admins) {
                        System.out.println(cadaAdm.toString());
                    }
                    adm.borrar(adm.buscarAdminPorId(sc.nextInt()));
                }
                sc.close();
            }
            case 2: {
                System.out.println("Ingrese el identificador del Administrador");
                Scanner sc = new Scanner(System.in);
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.close();
                adm.borrar(adm.buscarAdminPorId(id));
            }
            case 3: { gestionarAdmins(sesion); }
        }
    }


    public static void gestionarPacientes() {
    }

    public static void gestionarTurnos() {
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
//    public static Medico buscarMedico() {
//        ArrayList<String> datosMedico = login(planillaMedicos);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//        return new Medico(datosMedico.get(1),
//                datosMedico.get(2),
//                Integer.parseInt(datosMedico.get(3)),
//                datosMedico.get(4),
//                datosMedico.get(5),
//                datosMedico.get(6),
//                datosMedico.get(7),
//                LocalDate.parse(datosMedico.get(8), formatter),
//                datosMedico.get(9).charAt(0),
//                datosMedico.get(10),
//                datosMedico.get(11),
//                datosMedico.get(12)
//        );
//    }

    /**
     * TODO Este método debe migrarse a la clase Medico
     * @param sesion
     */

    public static void menuMedico(Sesion sesion) {
        limpiarPantalla();
        String tratamiento = String.valueOf(sesion.obtenerSexo()).equals("M")? "Bienvenido, Dr. ": "Bienvenida, Dra. ";
        System.out.println(tratamiento + sesion.obtenerApellido() + "\n" +
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
        menuInicio(sesion);

    }
}

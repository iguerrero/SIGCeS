/*  El presente programa es un prototipo y es parte de los trabajos pr�cticos
 *  de la c�tedra de <b>Programaci�n Orientada a Objetos</b> de la carrera de Lic. en Inform�tica
 *  de la <b>Universidad Empresarial Siglo XXI</b>.
 * Puede verse online en <a href="https://github.com/iguerrero21/SIGCeS">Github</a>
 */
package edu.grupo9.sigces;

import edu.grupo9.sigces.dao.*;
//import edu.grupo9.sigces.gui.GUI;
import edu.grupo9.sigces.objects.Admin;
import edu.grupo9.sigces.objects.Medico;
import edu.grupo9.sigces.objects.Paciente;
import edu.grupo9.sigces.objects.Sesion;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.grupo9.sigces.Utilidades.*;
import static edu.grupo9.sigces.dao.MedicoDaoImpl.verAgendaDiaria;


/**
 * @author Ignacio Guerrero
 * @version 0.1 TP2
 * @Date: 27/04/2022 <br>
 * Puede verse online en <a href="https://github.com/iguerrero21/SIGCeS">Github</a>.
 */
public class Main {

    private static Sesion sesion;

    /**
     * Pantalla de Bienvenida.
     * Inicio de la aplicaci�n.
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
                Sistema Integral de Gesti�n de Centros de Salud""");
        dormirPor(3000);
        limpiarPantalla();
        /* El c�digo comentado sirve para cargar la base de datos la primera vez. */
//        conectar();
//        SQLiteDB.crearTablas();
//        SQLiteDB.agregarDatos();
    }

    /**
     * Men� inicial. Selecciona entre usuario M�dico y usuario Admin.
     */
    public static void menuInicio(Sesion sesion) {
        try{
            System.out.println("""
                    \u00BFEs usted M\u00E9dico o Administrador?\s
                    1. M\u00E9dico\s
                    2. Administrador""");
            switch (seleccion()) {
                case 1 -> {
                    MedicoDao med = new MedicoDaoImpl();
                    sesion = med.loginMedico(0);
                    menuMedico(sesion);
                }
                case 2 -> {
                    AdminDao adm = new AdminDaoImpl();
                    sesion = adm.loginAdmin(0);
                    menuAdmin(sesion);
                }
                default -> {
                    System.out.println("Por favor, elija una n\u00FAmero dentro de las opciones provistas.");
                    dormirPor(1000);
                    limpiarPantalla();
                    menuInicio(sesion);
                }
            }
        } catch (Exception e) {
            /* Esta excepci�n es en caso de que el usuario ingrese
             * una letra o un s�mbolo, en lugar de un entero.
             */
            System.out.println("Los datos no fueron encontrados. Por favor, intente nuevamente.");
            System.err.println(e.getMessage());
            dormirPor(1000);
            limpiarPantalla();
            if (sesion != null && sesion.obtenerRol().equals("M�dico")) {
                menuMedico(sesion);
            } else if (sesion != null && sesion.obtenerRol().equals("Admin")) {
                menuAdmin(sesion);
            } else {
                menuInicio(sesion);
            }
        }
    }

    /**
     * Muestra la opciones del perfil Administrador.
     * @param sesion Mantiene una sesi�n abierta.
     */
    public static void menuAdmin(Sesion sesion) {
        limpiarPantalla();
        String tratamiento = String.valueOf(sesion.obtenerSexo()).equals("M") ? "Bienvenido, " : "Bienvenida, ";
        System.out.println(tratamiento + sesion.obtenerNombre() + "\n" +
                "Selecciona una de las siguientes opciones: \n" +
                "\033[31m1. Gestionar Turnos  \033[0m \n" +
                "2. Gestionar Pacientes \n" +
                "3. Gestionar M\u00E9dicos \n" +
                "4. Gestionar Administradores \n" +
                "0. Cerrar Sesi�n");
        switch (seleccion()) {
            case 1 -> gestionarTurnos();
            case 2 -> gestionarPacientes(sesion);
            case 3 -> gestionarMedicos(sesion);
            case 4 -> gestionarAdmins(sesion);
            case 0 -> cerrarSesion();
        }
    }

    /**
     *  Cierra la sesi�n y finaliza la ejecuci�n de la aplicaci�n.
     */
    private static void cerrarSesion() {
        System.out.println("Gracias por utilizar SIGCeS!");
        dormirPor(3000);
        System.exit(0);
    }

    /* Gesti�n CRUD de M�dicos desde el perfil Admin. */
    public static void gestionarMedicos(Sesion sesion) {
//        Medico medico = null;
        System.out.println("""
                �Qu� desea hacer? \s
                1. Cargar un nuevo M�dico \s
                2. Modificar Datos \s
                3. Eliminar M�dico \s
                4. Gestionar Agenda \s
                0. Volver al men� anterior \s
                """);

        MedicoDao med = new MedicoDaoImpl();
        AgendaDao agd = new AgendaDaoImpl();
        switch (seleccion()) {
            case 1 -> { med.cargarNuevo(); gestionarMedicos(sesion); }
            case 2 -> modificarMedico(sesion);
            case 3 -> eliminarMedico(sesion);
            case 4 -> agd.gestionarAgenda(sesion);
            case 0 -> menuAdmin(sesion);
        }
    }

    public static void modificarMedico(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s
                el identificador de m�dico que desea modificar.\s
                1. Nombre y Apellido \s
                2. Identificador \s
                0. Volver al men� anterior
                """);
        Scanner scanner = new Scanner(System.in);
        MedicoDao med = new MedicoDaoImpl();
        Medico medico;
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                medico = med.buscarMedicosPorNombre(nombre, apellido);
                if (medico == null) {
                    System.out.println("La persona no ha sido encontrada.");
                    dormirPor(2000);
                    gestionarMedicos(sesion);
                }
                med.modificar(medico);
                scanner.close();
            }
            case 2 -> {
                System.out.println("Ingrese el identificador del M�dico");
                Scanner sc = new Scanner(System.in);
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.close();
                medico = med.buscarMedicoPorId(id);
                med.modificar(medico);
            }
            case 0 -> gestionarMedicos(sesion);
        }
    }

    public static void eliminarMedico(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s
                el identificador de m�dico que desea eliminar.\s
                1. Nombre y Apellido \s
                2. Identificador \s
                0. Volver al men� anterior
                """);
        Scanner scanner = new Scanner(System.in);
        MedicoDao med = new MedicoDaoImpl();
        Medico medico;
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                medico = med.buscarMedicosPorNombre(nombre, apellido);
                System.out.println("Usted est� por eliminar el registro de: ");
                System.out.println(medico.toString());
                System.out.println("�Desea continuar? (S/N)");
                String acepta = scanner.nextLine().toUpperCase();
                if (acepta.equals("S")) {
                    med.borrar(med.buscarMedicoPorId(medico.obtenerIdMedico()));
                    System.out.println("El registro ha sido eliminado.");
                } else {
                    eliminarMedico(sesion);
                }
                scanner.close();
            }
            case 2 -> {
                System.out.println("Ingrese el identificador del M�dico");
                Scanner sc = new Scanner(System.in);
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.close();
                med.borrar(med.buscarMedicoPorId(id));
            }
            case 0 -> gestionarMedicos(sesion);
        }
    }

    /* Gesti�n CRUD de Administradores desde el perfil Admin */
    public static void gestionarAdmins(Sesion sesion) {
        System.out.println("""
                �Qu� desea hacer? \s
                1. Cargar un nuevo Administrador \s
                2. Modificar Datos \s
                3. Eliminar Administrador \s
                0. Volver al men� anterior \s
                """);

        AdminDao adm = new AdminDaoImpl();
        switch (seleccion()) {
            case 1 -> { adm.cargarNuevo(); gestionarAdmins(sesion); }
            case 2 -> modificarAdmin(sesion);
            case 3 -> eliminarAdmin(sesion);
            case 0 -> menuAdmin(sesion);
        }
    }

    public static void modificarAdmin(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s
                el identificador de m�dico que desea modificar.\s
                1. Nombre y Apellido \s
                2. Identificador \s
                0. Volver al men� anterior
                """);
        Scanner scanner = new Scanner(System.in);
        AdminDao adm = new AdminDaoImpl();
        Admin admin;
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                ArrayList<Admin> admins = adm.buscarAdminsPorNombre(nombre, apellido);
                if (admins.size() == 1) {
                    admin = adm.buscarAdminPorId(admins.get(0).obtenerIdAdmin());
                    adm.modificar(admin);
                } else if (admins.size() > 1) {
                    System.out.println("""
                            Se encontraron m�s de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Admin cadaAdm : admins) {
                        System.out.println(cadaAdm.toString());
                    }
                    admin = adm.buscarAdminPorId(scanner.nextInt());
                    adm.modificar(admin);
                } else {
                    System.out.println("La persona no ha sido encontrada.");
                    dormirPor(2000);
                    gestionarAdmins(sesion);
                }
                scanner.close();
            }
            case 2 -> {
                System.out.println("Ingrese el identificador del Administrador");
                Scanner sc = new Scanner(System.in);
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.close();
                admin = adm.buscarAdminPorId(id);
                adm.modificar(admin);
            }
            case 0 -> gestionarAdmins(sesion);
        }
    }

    public static void eliminarAdmin(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s
                el identificador del administrador que desea eliminar.\s
                1. Nombre y Apellido \s
                2. Identificador \s
                0. Volver al men� anterior
                """);
        Scanner scanner = new Scanner(System.in);
        AdminDao adm = new AdminDaoImpl();
//        Admin admin = null;
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                ArrayList<Admin> admins = adm.buscarAdminsPorNombre(nombre, apellido);
                if (admins.size() == 1) {
                    System.out.println("Usted est� por eliminar el registro de: ");
                    System.out.println(admins.get(0).toString());
                    System.out.println("�Desea continuar? (S/N)");
                    String acepta = scanner.nextLine().toUpperCase();
                    if (acepta.equals("S")) {
                        adm.borrar(adm.buscarAdminPorId(admins.get(0).obtenerIdAdmin()));
                        System.out.println("El registro ha sido eliminado.");
                    } else {
                        eliminarAdmin(sesion);
                    }

                } else {
                    System.out.println("""
                            Se encontraron m�s de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Admin cadaAdm : admins) {
                        System.out.println(cadaAdm.toString());
                    }
                    adm.borrar(adm.buscarAdminPorId(scanner.nextInt()));
                }
            }
            case 2 -> {
                System.out.print("Id: ");
                int id = scanner.nextInt();
                adm.borrar(adm.buscarAdminPorId(id));
            }
            case 0 -> gestionarAdmins(sesion);
        }
        scanner.close();
    }

    /* Gesti�n CRUD de Pacientes desde el perfil Admin */
    public static void gestionarPacientes(Sesion sesion) {
        System.out.println("""
                �Qu� desea hacer? \s
                1. Cargar un nuevo Paciente \s
                2. Modificar Datos \s
                3. Eliminar Paciente \s
                4. Buscar Paciente \s
                \033[31m5. Ver Historia Cl�nica\033[0m \s
                0. Volver al men� anterior \s
                """);

        PacienteDao pct = new PacienteDaoImpl();
        switch (seleccion()) {
            case 1 -> { pct.cargarNuevo(); gestionarPacientes(sesion); }
            case 2 -> { modificarPaciente(sesion); gestionarPacientes(sesion); }
            case 3 -> { eliminarPaciente(sesion); gestionarPacientes(sesion); }
            case 4 -> { buscarPaciente(sesion); gestionarPacientes(sesion); }
            case 5 -> verHistoriaClinica();
            case 0 -> menuAdmin(sesion);
        }
    }

    private static void buscarPaciente(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s
                el identificador de paciente.\s
                1. Nombre y Apellido \s
                2. Identificador \s
                0. Volver al men� anterior
                """);
        Scanner scanner = new Scanner(System.in);
        PacienteDao pct = new PacienteDaoImpl();
        Paciente paciente = null;
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                ArrayList<Paciente> pacientes = pct.buscarPacientePorNombre(nombre, apellido);
                if (pacientes.size() == 1) {
                    paciente = pct.buscarPacientePorId(pacientes.get(0).obtenerIdPaciente());
                    pct.imprimirPaciente(paciente);
                    System.out.println("Oprima ENTER para continuar");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (pacientes.size() > 1) {
                    System.out.println("""
                            Se encontraron m�s de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Paciente cadaPct : pacientes) {
                        pct.imprimirPaciente(cadaPct);
                    }
                    System.out.println("Oprima ENTER para continuar");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    paciente = pct.buscarPacientePorId(scanner.nextInt());


                } else {
                    System.out.println("La persona no ha sido encontrada.");
                    dormirPor(2000);
                    gestionarPacientes(sesion);
                }
            }
            case 2 -> {
                System.out.print("Id: ");
                int id = scanner.nextInt();
                paciente = pct.buscarPacientePorId(id);
                pct.imprimirPaciente(paciente);
                System.out.println("Oprima ENTER para continuar");
                try {
                    System.in.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case 0 -> gestionarPacientes(sesion);
        }
    }

    private static void eliminarPaciente(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s
                el identificador de paciente que desea eliminar.\s
                1. Nombre y Apellido \s
                2. Identificador \s
                0. Volver al men� anterior
                """);
        Scanner scanner = new Scanner(System.in);
        PacienteDao pct = new PacienteDaoImpl();
        Paciente paciente;
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                ArrayList<Paciente> pacientes = pct.buscarPacientePorNombre(nombre, apellido);
                if (pacientes.size() == 1) {
                    paciente = pct.buscarPacientePorId(pacientes.get(0).obtenerIdPaciente());
                    pct.borrar(paciente);
                } else if (pacientes.size() > 1) {
                    System.out.println("Se encontraron m�s de una persona con esos datos.");
                    for (Paciente cadaPct : pacientes) {
                        pct.imprimirPaciente(cadaPct);
                        System.out.println("ENTER para continuar");
                        try {
                            System.in.read();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Por favor, ingrese el Id de la persona que desea borrar.");
                    paciente = pct.buscarPacientePorId(scanner.nextInt());
                    pct.borrar(paciente);
                } else {
                    System.out.println("La persona no ha sido encontrada.");
                    dormirPor(2000);
                    gestionarPacientes(sesion);
                }
            }
            case 2 -> {
                System.out.print("Id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                paciente = pct.buscarPacientePorId(id);
                pct.borrar(paciente);
            }
            case 0 -> gestionarPacientes(sesion);
        }
    }

    private static void modificarPaciente(Sesion sesion) {
        System.out.println("""
                Por favor, ingrese el nombre y apellido, o \s
                el identificador de paciente que desea modificar.\s
                1. Nombre y Apellido \s
                2. Identificador \s
                0. Volver al men� anterior
                """);
        Scanner scanner = new Scanner(System.in);
        PacienteDao pct = new PacienteDaoImpl();
        Paciente paciente;
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                ArrayList<Paciente> pacientes = pct.buscarPacientePorNombre(nombre, apellido);
                if (pacientes.size() == 1) {
                    paciente = pct.buscarPacientePorId(pacientes.get(0).obtenerIdPaciente());
                    pct.modificar(paciente);
                } else if (pacientes.size() > 1) {
                    System.out.println("""
                            Se encontraron m�s de una persona con esos datos.
                            Por favor, elija la que desea por su identificador.
                            """);
                    for (Paciente cadaPct : pacientes) {
                        pct.imprimirPaciente(cadaPct);
                        System.out.println("Oprima ENTER para continuar");
                        try {
                            System.in.read();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    paciente = pct.buscarPacientePorId(scanner.nextInt());
                    pct.modificar(paciente);
                } else {
                    System.out.println("La persona no ha sido encontrada.");
                    dormirPor(2000);
                    gestionarPacientes(sesion);
                }
            }
            case 2 -> {
                System.out.print("Id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                paciente = pct.buscarPacientePorId(id);
                pct.modificar(paciente);
            }
            case 0 -> gestionarPacientes(sesion);
        }
    }

    /* Gestion CRUD de Turnos desde el perfil Admin */
    public static void gestionarTurnos() {
        System.out.println("""
                �Qu� desea hacer? \s
                1. Cargar un nuevo Turno \s
                2. Modificar Turno \s
                3. Eliminar Turno \s
                4. Buscar Turnos \s
                0. Volver al men� anterior \s
                """);

        TurnoDao trn = new TurnoDaoImpl();
        AgendaDao agd = new AgendaDaoImpl();
        switch (seleccion()) {
            case 1 -> { trn.generarNuevoTurno(); gestionarTurnos(); }
            case 2 -> { trn.modificarTurno(); gestionarTurnos(); }
            case 3 -> { trn.eliminarTurno(); gestionarTurnos(); }
            case 4 -> { trn.listarTurnos(agd.buscarIdAgenda(), LocalDate.now()); gestionarTurnos(); }
            case 5 -> verHistoriaClinica();
            case 0 -> menuAdmin(sesion);
        }
    }

    /**
     * Muestra la opciones del perfil M�dico.
     * @param sesion Mantiene una sesi�n abierta.
     */
    public static void menuMedico(Sesion sesion) {
        limpiarPantalla();
        String tratamiento = String.valueOf(sesion.obtenerSexo()).equals("M")? "Bienvenido, Dr. ": "Bienvenida, Dra. ";
        System.out.println(tratamiento + sesion.obtenerApellido() + "\n" +
                            "Seleccione una de las siguientes opciones: \n" +
                            "1. Ver la agenda de hoy \n" +
                            "2. Ver Historia Cl\u00EDnica \n" +
                            "0. Cerrar Sesi�n");
        switch (seleccion()) {
            case 1 -> verAgendaDiaria(sesion);
            case 2 -> verHistoriaClinica();
            case 0 -> cerrarSesion();
        }
    }

    private static void verHistoriaClinica() {

    }

    public static void main(String[] args) {
//        launch(args);
        bienvenida();
        menuInicio(sesion);

    }
}

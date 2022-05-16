package edu.grupo9.sigces;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static edu.grupo9.sigces.Utiles.*;

public class Admin extends Usuario implements VarsGlobales {

    private static String idAdmin;
    private String clave;

   /* * * CONSTRUCTORES * * */
    public Admin() {}

    public Admin(String nombre,
                 String apellido,
                 int dni,
                 String clave,
                 String domicilio,
                 String telefono,
                 String email,
                 LocalDate fechaNac,
                 char sexo,
                 String s1,
                 String s2,
                 String s3) //s1, s2, s3 sin definir aún.
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.clave = clave;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
    }

    /* * * GETTERS Y SETTERS * * */
    public String obtenerClave() {
        return clave;
    }
    public void establecerClave(String clave) {
        this.clave = clave;
    }

    /* METODOS DE CLASE */

    public static Admin buscarAdmin() {
        ArrayList<String> datosAdmin = Main.login(planillaAdmins);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return new Admin(datosAdmin.get(1),
                datosAdmin.get(2),
                Integer.parseInt(datosAdmin.get(3)),
                datosAdmin.get(4),
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
    static void menuAdmin(Admin admin) {
        limpiarPantalla();
        String tratamiento = String.valueOf(admin.obtenerSexo()).equals("M") ? "Bienvenido, " : "Bienvenida, ";
        System.out.println(tratamiento + admin.obtenerNombre() + "\n" +
                "Seleccione una de las siguientes opciones: \n" +
                "\033[31m1. Gestionar Turnos  \033[0m \n" +
                "\033[31m2. Ver Historia Cl\u00EDnica \033[0m \n" +
                "3. Gestionar M\u00E9dicos \n" +
                "\033[31m4. Gestionar Administradores \033[0m");
        switch (seleccion()) {
            case 1 -> Admin.gestionarTurnos();
            case 2 -> Admin.gestionarPacientes();
            case 3 -> Admin.gestionarMedicos();
            case 4 -> Admin.gestionarAdmins();
        }
    }

    /* * * GESTIÓN DE MÉDICOS * * */
    public static void gestionarMedicos() {
        System.out.println("""
                ¿Qué desea hacer? \s
                1. Cargar un nuevo Médico \s
                2. Modificar Datos \s
                3. Eliminar Médico \s
                \033[31m4. Gestionar Agenda\033[0m
                5. Volver al menú anterior""");

        switch (seleccion()){
            case 1 -> cargarNuevoMedico();
            case 2 -> modificarMedico();
            case 3 -> eliminarMedico();
            case 5 -> menuAdmin(buscarAdmin());
        }
    }

    /**
     *  Crear un nuevo objeto Medico
     */
    public static void cargarNuevoMedico() {
        System.out.println("Por favor, ingrese los datos del nuevo Médico.");
        Scanner scanner = new Scanner(System.in);
        Medico medico = new Medico();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.print("Nombre: ");
        medico.establecerNombre(scanner.nextLine());
        System.out.print("Apellido: ");
        medico.establecerApellido(scanner.nextLine());
        System.out.print("DNI: ");
        medico.establecerDni(scanner.nextInt());
        String claveValida;
        while (!validarClave(claveValida = scanner.nextLine())){ System.out.println("Clave (debe tener 4 dígitos):");}
        medico.establecerClave(claveValida);
        System.out.print("Domicilio: ");
        medico.establecerDomicilio(scanner.nextLine());
        System.out.print("Teléfono: ");
        medico.establecerTelefono(scanner.nextLine());
        System.out.print("eMail: ");
        medico.establecerEmail(scanner.nextLine());
        System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
        medico.establecerFechaNac(LocalDate.parse(scanner.nextLine(), formatter));
        System.out.print("Sexo: ");
        medico.establecerSexo(scanner.nextLine().charAt(0));
        System.out.print("Matrícula Prov.: ");
        medico.establecerMatriculaProv(scanner.nextLine());
        System.out.print("Matrícula Nac.: ");
        medico.establecerMatriculaNac(scanner.nextLine());
        System.out.print("Especialidades (Separadas por coma): ");
        medico.establecerEspecialidades(scanner.nextLine());
        cargarPlanilla(establecerDatos(medico), planillaMedicos);
    }

    /**
     * Cargar los datos del objeto Medico en un arreglo
     * @param medico Medico
     * @return <i>ArrayList</i> con los <b>datos</b> del objeto Medico
     */
    public static String establecerDatos (Medico medico) {
        ArrayList<String> datos = new ArrayList<>();
        try {
            datos.add(String.valueOf(contarLineas(planillaMedicos)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        datos.add(medico.obtenerNombre());
        datos.add(medico.obtenerApellido());
        datos.add(String.valueOf(medico.obtenerDni()));
        datos.add(medico.obtenerClave());
        datos.add(medico.obtenerDomicilio());
        datos.add(medico.obtenerTelefono());
        datos.add(medico.obtenerEmail());
        datos.add(String.valueOf(medico.obtenerFechaNac()));
        datos.add(String.valueOf(medico.obtenerSexo()));
        datos.add(medico.obtenerMatriculaProv());
        datos.add(medico.obtenerMatriculaNac());
        datos.add(medico.obtenerEspecialidades());
        System.out.println("Datos establecidos");
        return datos.toString().replace("[", "").replace("]", "").replace(",",";");
    }

    /**
     * Cargar los datos en una nueva línea en la planilla medicos.csv
     * @param nuevaLinea
     * @param planilla
     */
    public static void cargarPlanilla(String nuevaLinea, String planilla) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(planilla, true));
            bw.newLine();
            bw.write(nuevaLinea);
            bw.flush();
            bw.close();
        } catch (Exception e) {

        }
    }

    public static int contarLineas(String planilla) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(planilla));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines + 1;
    }

    public static void modificarMedico() {
        System.out.println("Por favor, ingrese el nombre y apellido del médico.");
        Scanner scanner = new Scanner(System.in);
        Medico medico = new Medico();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
    }

    public static void eliminarMedico() {
        System.out.println("Por favor, ingrese el nombre y apellido del médico.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        try {
            BufferedReader br = new BufferedReader(new FileReader(planillaMedicos));
            StringBuilder salida = new StringBuilder();
            String linea;
            String porEliminar = null;
            while ((linea = br.readLine()) != null) {
                String[] values = linea.split(";");
                String idPlanilla = values[0].trim();
                String nombrePlanilla = values[1].trim();
                String apellidoPlanilla = values[2].trim();
                if (Objects.equals(nombre, nombrePlanilla) && Objects.equals(apellido, apellidoPlanilla)) {
                    porEliminar = linea;
                    linea = idPlanilla + "; Médico eliminado;;;;;;;;;;;";
                }
                salida.append(linea + "\n");
            }
            br.close();
            System.out.println("""
                        Por favor, confirme la operación.\s
                        Está a punto de eliminar a: \s"""
                        + porEliminar + """
                        1. Sí \s
                        2. No
                        """);
            int seleccion = scanner.nextInt();
            if (seleccion == 1) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(planillaMedicos));
                bw.write(String.valueOf(salida));
                bw.flush();
                bw.close();
            }
            gestionarMedicos();
        } catch (IOException f) {

        }
    }

    public static void gestionarPacientes() {}

    public static void gestionarTurnos() {}

    public static void gestionarAdmins() {}

    public static ArrayList<String> leerCSV(String nombre, String apellido, String planilla) {
        // Abre planilla y lee cada línea.
        try (BufferedReader br = new BufferedReader(new FileReader(planilla))) {
            String s;
            br.close();
            // Lee línea por línea
            while ((s = br.readLine()) != null) {
                // Divide cada línea y la divide en cada punto y coma.
                String[] values = s.split(";");
                String nombrePlanilla = values[1].trim();
                String apellidoPlanilla = values[2].trim();
                if (Objects.equals(nombre, nombrePlanilla) && Objects.equals(apellido, apellidoPlanilla)) {
                    return new ArrayList<>(Arrays.asList(values));
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * En este caso, valida que la clave sea numérica y de 4 dígitos.
     * Se puso aparte para poder modificar el método de validación sin
     * tener que tocar el código.
     * @param clave
     * @return boolean
     */
    public static boolean validarClave(String clave) {
        return clave.length() == 4;
    }
}

package edu.grupo9.sigces;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Admin extends Usuario implements VarsGlobales {

    private static String idAdmin;
    private String clave;

   /* CONSTRUCTORES */
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

    /* GETTERS Y SETTERS */
    public String obtenerClave() {
        return clave;
    }
    public void establecerClave(String clave) {
        this.clave = clave;
    }

    /* METODOS DE CLASE */

    /* MÉDICOS */
    public static void gestionarMedicos() {
        System.out.println("""
                ¿Qué desea hacer? \s
                1. Cargar un nuevo Médico \s
                2. Modificar Datos \s
                3. Eliminar Médico \s
                4. Gestionar Agenda""");
        switch (Main.seleccion()){
            case 1 -> cargarNuevoMedico();
        }
    }

    /* * * CARGAR NUEVO MÉDICO * * */

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
        System.out.print("Clave: ");
        medico.establecerClave(scanner.nextLine());
        scanner.nextLine(); // No sé. Salta Domicilio sino...
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

    public static void gestionarPacientes() {}
    public static void gestionarTurnos() {}
    public static void gestionarAdmins() {}

    public static ArrayList<String> leerCSV(String nombre, String apellido, String planilla) throws IOException {
        // Abre planilla y lee cada línea.
        try (BufferedReader br = new BufferedReader(new FileReader(planilla))) {
            String s;
            // Lee línea por línea
            while ((s = br.readLine()) != null) {
                // Divide cada línea y la divide en cada punto y coma.
                String[] values = s.split(";");
                String nombrePlanilla = values[1];
                String apellidoPlanilla = values[2];
                if (Objects.equals(nombre, nombrePlanilla) && Objects.equals(apellido, apellidoPlanilla)) {
                    return new ArrayList<>(Arrays.asList(values));
                }
            }
            br.close();
            throw new IOException("Archivo no encontrado.");
        }
    }
}

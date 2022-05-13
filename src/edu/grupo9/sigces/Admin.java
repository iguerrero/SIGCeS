package edu.grupo9.sigces;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Admin extends Usuario implements VarsGlobales {

    private static String idAdmin;
    private int clave;

   /* CONSTRUCTORES */
    public Admin() {}

    public Admin(String nombre,
                 String apellido,
                 int dni,
                 int clave,
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
    public int obtenerClave() {
        return clave;
    }
    public void establecerClave(int clave) {
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
        medico.establecerClave(scanner.nextInt());
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

    public static String establecerDatos (Medico medico) {
        ArrayList<String> datos = null;
        String nombre = medico.obtenerNombre();
        datos.add(nombre);
        String apellido = medico.obtenerApellido();
        datos.add(apellido);
        String dni = String.valueOf(medico.obtenerDni());
        datos.add(dni);
        String clave = String.valueOf(medico.obtenerClave());
        datos.add(clave);
        String domicilio = medico.obtenerDomicilio();
        datos.add(domicilio);
        String telefono = medico.obtenerTelefono();
        datos.add(telefono);
        String eMail = medico.obtenerEmail();
        datos.add(eMail);
        String fechaNac = String.valueOf(medico.obtenerFechaNac());
        datos.add(fechaNac);
        String sexo = String.valueOf(medico.obtenerSexo());
        datos.add(sexo);
        String matriculaProv = medico.obtenerMatriculaProv();
        datos.add(matriculaProv);
        String matriculaNac = medico.obtenerMatriculaNac();
        datos.add(matriculaNac);
        String especialidades = medico.obtenerEspecialidades();
        datos.add(especialidades);
        System.out.println("Datos establecidos");
        return datos.toString().replace("[", "").replace("]", "").replace(",",";");
    }

    public static void cargarPlanilla(String nuevaLinea, String planilla) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(planilla));
            bw.write(nuevaLinea);
        } catch (Exception e) {

        }
    }


    //    public void nuevoMedico() {}
    public static void gestionarPacientes() {}
    //    public void nuevoPaciente() {}
    public static void gestionarTurnos() {}
    //    public void nuevoTurno() {}
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

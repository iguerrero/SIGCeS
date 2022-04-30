package edu.grupo9.sigces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Admin extends Usuario{

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
    public static void gestionarMedicos() {
        System.out.println("""
                ¿Qué desea hacer? \s
                1. Cargar un nuevo Médico \s
                2. Modificar Datos \s
                3. Eliminar Médico \s
                4. Gestionar Agenda""");
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
            throw new IOException("Archivo no encontrado.");
        }
    }
}

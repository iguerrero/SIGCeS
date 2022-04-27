package edu.grupo9.sigces;

import java.time.LocalDate;

class Usuario {
    /**
     * La clase Usuario es el esqueleto de las clases
     * Paciente, Medico y Admin. Contiene variables del tipo
     * nombre, apellido, dni, domicilio etc.
     */

    /* Declaración de variables de la clase */
    private static String nombre;
    private static String apellido;
    private static int dni;
    private static String domicilio;
    private static String telefono;
    private static String email;
    protected static LocalDate fechaNac;
    private static char sexo;

    public Usuario() {}

    /*
     * getters y setters de la clase
     */
    public static void setNombre(String nombre) {
        Usuario.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public static void setApellido(String apellido) {
        Usuario.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public static void setDni(int dni) {
        Usuario.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public static void setDomicilio(String domicilio) {
        Usuario.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public static void setTelefono(String telefono) {
        Usuario.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public static void setEmail(String email) {
        Usuario.email = email;
    }

    public String getEmail() {
        return email;
    }

    public static void setFechaNac(LocalDate fechaNac) {
        Usuario.fechaNac = fechaNac;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public static void setSexo(char sexo) {
        Usuario.sexo = sexo;
    }

    public char getSexo() {
        return sexo;
    }
}

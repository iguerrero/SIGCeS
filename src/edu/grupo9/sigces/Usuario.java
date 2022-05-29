package edu.grupo9.sigces;

import java.time.LocalDate;

public abstract class Usuario {
    /**
     * La clase Usuario es el esqueleto de las clases
     * Paciente, Medico y Admin. Contiene variables del tipo
     * nombre, apellido, dni, domicilio etc.
     */

    /* Declaración de variables de la clase */
    protected String nombre;
    protected String apellido;
    protected int dni;
    protected String domicilio;
    protected String telefono;
    protected String email;
    protected LocalDate fechaNac;
    protected char sexo;

    protected Usuario() {}

    protected Usuario(String nombre,
                   String apellido,
                   int dni,
                   String domicilio,
                   String telefono,
                   String email,
                   LocalDate fechaNac,
                   char sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
    }

    /*
     * getters y setters de la clase
     */
    public void establecerNombre(String nombre) {
        this.nombre = nombre;
    }
    public String obtenerNombre() {
        return nombre;
    }
    public void establecerApellido(String apellido) {
        this.apellido = apellido;
    }

    public String obtenerApellido() {
        return apellido;
    }

    public void establecerDni(int dni) {
        this.dni = dni;
    }

    public int obtenerDni() {
        return dni;
    }

    public void establecerDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String obtenerDomicilio() {
        return domicilio;
    }

    public void establecerTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String obtenerTelefono() {
        return telefono;
    }

    public void establecerEmail(String email) {
        this.email = email;
    }

    public String obtenerEmail() {
        return email;
    }

    public void establecerFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public LocalDate obtenerFechaNac() {
        return fechaNac;
    }

    public void establecerSexo(char sexo) {
        this.sexo = sexo;
    }

    public char obtenerSexo() {
        return sexo;
    }
}

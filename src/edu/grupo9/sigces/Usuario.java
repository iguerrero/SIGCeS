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

    public Usuario() {}

    public Usuario(String nombre,
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getSexo() {
        return sexo;
    }
}

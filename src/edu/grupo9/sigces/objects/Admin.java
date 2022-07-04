package edu.grupo9.sigces.objects;

import java.time.LocalDate;

public class Admin extends Usuario {

    private int idAdmin;
    private String clave;

    /* * * CONSTRUCTORES * * */
    public Admin() {
    }

    public Admin(int idAdmin,
                 String nombre,
                 String apellido,
                 int dni,
                 String clave,
                 String domicilio,
                 String telefono,
                 String email,
                 LocalDate fechaNac,
                 String sexo) {
        this.idAdmin = idAdmin;
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

    public int obtenerIdAdmin() { return idAdmin; }

    public void establecerIdAdmin(int idAdmin) { this.idAdmin = idAdmin; }

    public String obtenerClave() {
        return clave;
    }

    public void establecerClave(String clave) {
        this.clave = clave;
    }

}


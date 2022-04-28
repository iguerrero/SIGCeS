package edu.grupo9.sigces;

import java.time.LocalDate;

public class Admin extends Usuario{

    private static String idAdmin;
    private int clave;

    public Admin(String nombre,
                 String apellido,
                 int dni,
                 int clave,
                 String domicilio,
                 String telefono,
                 String email,
                 LocalDate fechaNac,
                 char sexo,
                 String s,
                 String s1,
                 String s2) {
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

//    public void Admin() {}
//    public void buscarMedico() {}
//    public void nuevoMedico() {}
//    public void buscarPaciente() {}
//    public void nuevoPaciente() {}
//    public void nuevoTurno() {}

//    public static void cargarUsuarioNuevo() {} // usuario administrativo
//    public static void gestionarTurnos(idAgenda) {} // usuario administrativo


    public int obtenerClave() {
        return clave;
    }

    public void establecerClave(int clave) {
        this.clave = clave;
    }
}

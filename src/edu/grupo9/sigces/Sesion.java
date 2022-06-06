package edu.grupo9.sigces;

public class Sesion {

    private int idSesion;
    private String nombre;
    private String apellido;
    private String rol;
    private String sexo;

    public Sesion() {
    }

    public Sesion(int idSesion, String nombre, String apellido, String rol, String sexo) {
        this.idSesion = idSesion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.sexo = sexo;
    }

    public int obtenerIdSesion() {
        return idSesion;
    }

    public void establecerIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void establecerNombre(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerApellido() {
        return apellido;
    }

    public void establecerApellido(String apellido) {
        this.apellido = apellido;
    }

    public String obtenerRol() {
        return rol;
    }

    public void establecerRol(String rol) {
        this.rol = rol;
    }

    public String obtenerSexo() {
        return sexo;
    }

    public void establecerSexo(String sexo) {
        this.sexo = sexo;
    }
}

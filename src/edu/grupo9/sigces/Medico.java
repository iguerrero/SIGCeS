package edu.grupo9.sigces;

import java.time.LocalDate;
import java.util.ArrayList;

public class Medico extends Usuario{

    private String idMedico;
    private int clave;
    private String matriculaProv;
    private String matriculaNac;
    private String especialidades;

    /**
     * Constructor por defecto de la clase.
     */
    public Medico() {}

    public Medico(String nombre,
                  String apellido,
                  int dni,
                  int clave,
                  String domicilio,
                  String telefono,
                  String email,
                  LocalDate fechaNac,
                  char sexo,
                  String matriculaProv,
                  String matriculaNac,
                  String especialidades) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.clave = clave;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.matriculaProv = matriculaProv;
        this.matriculaNac = matriculaNac;
        this.especialidades = especialidades;
    }

    /*
     * getters y setters de la clase
     */
    public int getClave() { return clave; }

    public void setClave(int clave) { this.clave = clave; }

    public String getMatriculaProv() { return matriculaProv; }

    public void setMatriculaProv(String matriculaProv) {
        this.matriculaProv = matriculaProv;
    }

    public String getMatriculaNac() {
        return matriculaNac;
    }

    public void setMatriculaNac(String matriculaNac) {
        this.matriculaNac = matriculaNac;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

//    public void buscarPaciente() {}
//    public void verAgenda() {}
//    public void buscarHistoriaClinica() {}
//    public void nuevaHistoriaClinica() {}

//    public static void verTurnosHoy(idAgenda) {} // usuario médico
//    public static void verPaciente(nombre, apellido) {}// usuario médico
//    public static void verHistoriaClinica(nombre, apellido) {} // usuario médico
//    public static void cargarNuevoDiagnostico() {} // usuario médico


}

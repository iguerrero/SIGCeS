package edu.grupo9.sigces;

import java.time.LocalDate;

public class Medico extends Usuario{

    private String idMedico;
    private String clave;
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
                  String clave,
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
    public String obtenerClave() { return clave; }

    public void establecerClave(String clave) { this.clave = clave; }

    public String obtenerMatriculaProv() { return matriculaProv; }

    public void establecerMatriculaProv(String matriculaProv) {
        this.matriculaProv = matriculaProv;
    }

    public String obtenerMatriculaNac() {
        return matriculaNac;
    }

    public void establecerMatriculaNac(String matriculaNac) {
        this.matriculaNac = matriculaNac;
    }

    public String obtenerEspecialidades() {
        return especialidades;
    }

    public void establecerEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }
    /**
     * TODO
     */
    public void verPaciente() {  }
    /**
     * TODO
     */
    public void gestionarAgenda() {  }
    /**
     * TODO
     */
    public void verHistoriaClinica() {  }
    /**
     * TODO
     */
    public void cargarDiagnóstico() {  }
    /**
     * TODO
     */
    public static void verTurnosHoy(String idMedico) {  }

}

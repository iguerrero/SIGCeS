package edu.grupo9.sigces;

import java.time.LocalDate;
import java.time.Period;

public class Paciente extends Usuario{

    private String idPaciente;
    private static int edad;
    private int clave;

    public Paciente() {

    }

    public Paciente(String nombre,
                    String apellido,
                    int dni,
                    int clave,
                    String domicilio,
                    String telefono,
                    String email,
                    LocalDate fechaNac,
                    char sexo) {
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

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public static int getEdad(LocalDate fechaNac) {
            return Period.between(fechaNac, LocalDate.now()).getYears();
    }
}

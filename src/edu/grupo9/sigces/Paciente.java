package edu.grupo9.sigces;

import java.time.LocalDate;
import java.time.Period;

public class Paciente extends Usuario{

    private int idPaciente;
    private static int edad;

    public Paciente() {}

    public Paciente(String nombre,
                    String apellido,
                    int dni,
                    String domicilio,
                    String telefono,
                    String email,
                    LocalDate fechaNac,
                    String sexo) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.dni = dni;
            this.domicilio = domicilio;
            this.telefono = telefono;
            this.email = email;
            this.fechaNac = fechaNac;
            this.sexo = sexo;
    }

    public int obtenerIdPaciente() {
        return idPaciente;
    }

    public void establecerIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public static int calcularEdad(LocalDate fechaNac) {
            return Period.between(fechaNac, LocalDate.now()).getYears();
    }
}

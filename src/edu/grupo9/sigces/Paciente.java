package edu.grupo9.sigces;

import java.time.LocalDate;

public class Paciente extends Usuario{

    private static String idPaciente;
    private static int edad;

    public Paciente() {

    }

    public static String getIdPaciente() {
        return idPaciente;
    }

    public static void setIdPaciente(String idPaciente) {
        Paciente.idPaciente = idPaciente;
    }

    public static int getEdad(LocalDate fechaNac) {
        return edad;
    }
}

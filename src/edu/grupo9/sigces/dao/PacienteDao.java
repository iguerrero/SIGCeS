package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.Paciente;

import java.util.ArrayList;

public interface PacienteDao extends CRUD<Paciente> {
    ArrayList<Paciente> buscarPacientePorNombre(String nombre, String apellido);
    Paciente buscarPacientePorId(int id);

    void imprimirPaciente(Paciente paciente);
}

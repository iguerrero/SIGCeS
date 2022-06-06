package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.Sesion;

import java.util.ArrayList;

public interface MedicoDao extends CRUD<Medico> {

    ArrayList<Medico> buscarMedicosPorNombre(String nombre, String apellido);
    ArrayList<Medico> buscarMedicosPorEspecialidad();
    Medico buscarMedicoPorId(int id);
    Sesion loginMedico(int intento);

}

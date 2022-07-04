package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.objects.Medico;
import edu.grupo9.sigces.objects.Sesion;

import java.util.ArrayList;

public interface MedicoDao extends CRUD<Medico> {

    Medico buscarMedicosPorNombre(String nombre, String apellido);
    ArrayList<Medico> buscarMedicosPorEspecialidad();
    Medico buscarMedicoPorId(int id);
    Sesion loginMedico(int intento);

}

package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Admin;
import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.Turno;

import java.util.List;

public interface AdminDao {
    void crearMedico(Medico medico);
    List<Medico> listarMedicos();
    void modificarMedico(Medico medico);
    void borrarMedico(Medico medico);
    void crearAdmin(Admin admin);
    List<Admin> listarAdmins();
    void modificarAdmin(Admin admin);
    void borrarAdmin(Admin admin);
    void generarTurno(Turno turno);
    void borraTurno(Turno turno);
    
}

package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Admin;
import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.Turno;

import java.util.List;

public interface AdminDao extends CRUD<Admin>{

    void generarTurno(Turno turno);
    void borraTurno(Turno turno);

}

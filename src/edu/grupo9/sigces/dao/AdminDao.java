package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Admin;
import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.Sesion;
import edu.grupo9.sigces.Turno;

import java.util.ArrayList;
import java.util.List;

public interface AdminDao extends CRUD<Admin>{
    ArrayList<Admin> buscarAdminsPorNombre(String nombre, String apellido);

    Admin buscarAdminPorId(int id);

    Sesion loginAdmin(int intento);
}

package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Admin;
import edu.grupo9.sigces.Sesion;

import java.util.ArrayList;

public interface AdminDao extends CRUD<Admin>{
    ArrayList<Admin> buscarAdminsPorNombre(String nombre, String apellido);

    Admin buscarAdminPorId(int id);

    Sesion loginAdmin(int intento);
}

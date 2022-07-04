package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.objects.Admin;
import edu.grupo9.sigces.objects.Sesion;

import java.util.ArrayList;

public interface AdminDao extends CRUD<Admin>{
    ArrayList<Admin> buscarAdminsPorNombre(String nombre, String apellido);

    Admin buscarAdminPorId(int id);

    Sesion loginAdmin(int intento);
}

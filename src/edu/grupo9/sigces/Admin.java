package edu.grupo9.sigces;

import java.time.LocalDate;

public class Admin extends Usuario{

    private static String idAdmin;
    private static int clave;

    public Admin(String nombre,
                 String apellido,
                 int dni,
                 int clave,
                 String domicilio,
                 String telefono,
                 String email,
                 LocalDate fechaNac,
                 char sexo,
                 String s,
                 String s1,
                 String s2) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setClave(clave);
        setDomicilio(domicilio);
        setTelefono(telefono);
        setEmail(email);
        setFechaNac(fechaNac);
        setSexo(sexo);
    }

//    public void Admin() {}
//    public void buscarMedico() {}
//    public void nuevoMedico() {}
//    public void buscarPaciente() {}
//    public void nuevoPaciente() {}
//    public void nuevoTurno() {}

//    public static void cargarUsuarioNuevo() {} // usuario administrativo
//    public static void gestionarTurnos(idAgenda) {} // usuario administrativo




    public static int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        Admin.clave = clave;
    }
}

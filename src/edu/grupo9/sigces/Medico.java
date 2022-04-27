package edu.grupo9.sigces;

import java.time.LocalDate;
import java.util.ArrayList;

public class Medico extends Usuario{

    private static int clave;
    private static String matriculaProv;
    private static String matriculaNac;
    private static String especialidades;

    /**
     * Constructor por defecto de la clase.
     */
    public Medico() {}

    public Medico(String nombre,
                  String apellido,
                  int dni,
                  int clave,
                  String domicilio,
                  String telefono,
                  String email,
                  LocalDate fechaNac,
                  char sexo,
                  String matriculaProv,
                  String matriculaNac,
                  String especialidades) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setClave(clave);
        setDomicilio(domicilio);
        setTelefono(telefono);
        setEmail(email);
        setFechaNac(fechaNac);
        setSexo(sexo);
        setMatriculaProv(matriculaProv);
        setMatriculaNac(matriculaNac);
        setEspecialidades(especialidades);

    }

    /*
     * getters y setters de la clase
     */
    public int getClave() { return clave; }

    public static void setClave(int clave) { Medico.clave = clave; }

    public String getMatriculaProv() { return matriculaProv; }

    public static void setMatriculaProv(String matriculaProv) {
        Medico.matriculaProv = matriculaProv;
    }

    public String getMatriculaNac() {
        return matriculaNac;
    }

    public static void setMatriculaNac(String matriculaNac) {
        Medico.matriculaNac = matriculaNac;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public static void setEspecialidades(String especialidades) {
        Medico.especialidades = especialidades;
    }

//    public void buscarPaciente() {}
//    public void verAgenda() {}
//    public void buscarHistoriaClinica() {}
//    public void nuevaHistoriaClinica() {}

//    public static void verTurnosHoy(idAgenda) {} // usuario médico
//    public static void verPaciente(nombre, apellido) {}// usuario médico
//    public static void verHistoriaClinica(nombre, apellido) {} // usuario médico
//    public static void cargarNuevoDiagnostico() {} // usuario médico


}

package edu.grupo9.sigces;

public class Agenda {

    private static String idAgenda;

    public Agenda() {
    }

    public static String obtenerIdAgenda() {
        return idAgenda;
    }

    public static void establecerIdAgenda(String idAgenda) {
        Agenda.idAgenda = idAgenda;
    }
}

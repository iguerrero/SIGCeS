package edu.grupo9.sigces;

import org.threeten.extra.Interval;

import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda {

    enum Dia {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }
    private static String idAgenda;
    private LocalDate fecha;
    private ArrayList<Interval> intevalos;
    private ArrayList<Integer> turnos;

    public Agenda() {
    }

    public static String obtenerIdAgenda() {
        return idAgenda;
    }

    public static void establecerIdAgenda(String idAgenda) {
        Agenda.idAgenda = idAgenda;
    }

    public LocalDate obtenerFecha() {
        return fecha;
    }

    public void establecerFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Interval> obtenerIntevalos() {
        return intevalos;
    }

    public void establecerIntevalos(ArrayList<Interval> intevalos) {
        this.intevalos = intevalos;
    }

    public ArrayList<Integer> obtenerTurnos() {
        return turnos;
    }

    public void establecerTurnos(ArrayList<Integer> turnos) {
        this.turnos = turnos;
    }
}

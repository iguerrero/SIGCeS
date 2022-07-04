package edu.grupo9.sigces.objects;

import org.threeten.extra.Interval;

import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda {

    private int idAgenda;
    private LocalDate diaLaborable;
    private Interval intervaloLaborable;
    private ArrayList<Interval> intervalos;

    public Agenda() {
    }

    public Agenda(int idAgenda, LocalDate diaLaborable, Interval intervaloLaborable) {}

    public Integer obtenerIdAgenda() {
        return idAgenda;
    }

    public void establecerIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public LocalDate obtenerFecha() {
        return diaLaborable;
    }

    public void establecerFecha(LocalDate fecha) {
        this.diaLaborable = fecha;
    }

    public Interval obtenerIntervaloLaborable() {
        return intervaloLaborable;
    }

    public void establecerIntervaloLaborable(Interval intervaloLaborable) {
        this.intervaloLaborable = intervaloLaborable;
    }

    public ArrayList<Interval> obtenerIntervalos() {
        return intervalos;
    }

    public void establecerIntervalos(ArrayList<Interval> intervalos) {
        this.intervalos = intervalos;
    }

}

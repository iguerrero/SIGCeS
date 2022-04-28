package edu.grupo9.sigces;

import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda {

    private static String idAgenda;
    private int diaSemana;
    private LocalDate fechaInicio;
    private LocalDate fechaVigencia;
    private int duraciónIntevalo;
    private LocalDate horaInicio1;
    private LocalDate horaFin1;
    private LocalDate horaInicio2;
    private LocalDate horaFin2;
    private ArrayList<Integer> turnos;

    public Agenda() {
    }

    public static String obtenerIdAgenda() {
        return idAgenda;
    }

    public static void establecerIdAgenda(String idAgenda) {
        Agenda.idAgenda = idAgenda;
    }

    public int obtenerDiaSemana() {
        return diaSemana;
    }

    public void establecerDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalDate obtenerFechaInicio() {
        return fechaInicio;
    }

    public void establecerFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate obtenerFechaVigencia() {
        return fechaVigencia;
    }

    public void establecerFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public int obtenerDuraciónIntevalo() {
        return duraciónIntevalo;
    }

    public void establecerDuraciónIntevalo(int duraciónIntevalo) {
        this.duraciónIntevalo = duraciónIntevalo;
    }

    public LocalDate obtenerHoraInicio1() {
        return horaInicio1;
    }

    public void establecerHoraInicio1(LocalDate horaInicio1) {
        this.horaInicio1 = horaInicio1;
    }

    public LocalDate obtenerHoraFin1() {
        return horaFin1;
    }

    public void establecerHoraFin1(LocalDate horaFin1) {
        this.horaFin1 = horaFin1;
    }

    public LocalDate obtenerHoraInicio2() {
        return horaInicio2;
    }

    public void establecerHoraInicio2(LocalDate horaInicio2) {
        this.horaInicio2 = horaInicio2;
    }

    public LocalDate obtenerHoraFin2() {
        return horaFin2;
    }

    public void establecerHoraFin2(LocalDate horaFin2) {
        this.horaFin2 = horaFin2;
    }

    public ArrayList<Integer> obtenerTurnos() {
        return turnos;
    }

    public void establecerTurnos(ArrayList<Integer> turnos) {
        this.turnos = turnos;
    }
}

package edu.grupo9.sigces;

import java.time.LocalDate;
import org.threeten.extra.*;

enum Estado {
    LIBRE, CONSULTA, PRACTICA, CIRUGIA, NOLABOR
}
public class Turno {

    private String idTurno;
    private String idPaciente;
    private String idAgenda;
    private LocalDate diaHora;
    private Interval turno;
    private int duracion;
    private Estado estado;


    public Turno() {
    }

    public String obtenerIdTurno() {
        return idTurno;
    }

    public void establecerIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public String obtenerIdPaciente() {
        return idPaciente;
    }

    public void establecerIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String obtenerIdAgenda() {
        return idAgenda;
    }

    public void establecerIdAgenda(String idAgenda) {
        this.idAgenda = idAgenda;
    }

    public LocalDate obtenerDiaHora() {
        return diaHora;
    }

    public void establecerDiaHora(LocalDate diaHora) {
        this.diaHora = diaHora;
    }

    public Interval obtenerTurno() {
        return turno;
    }

    public void establecerTurno(Interval turno) {
        this.turno = turno;
    }

    public int obtenerDuracion() {
        return duracion;
    }

    public void establecerDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Estado obtenerEstado() {
        return estado;
    }

    public void establecerEstado(Estado estado) {
        this.estado = estado;
    }
}



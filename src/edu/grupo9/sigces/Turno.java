package edu.grupo9.sigces;

import java.time.LocalDate;

public class Turno {

    private String idTurno;
    private String idPaciente;
    private String idAgenda;
    private LocalDate diaYhora;
    private LocalDate estado;


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

    public LocalDate obtenerDiaYhora() {
        return diaYhora;
    }

    public void establecerDiaYhora(LocalDate diaYhora) {
        this.diaYhora = diaYhora;
    }

    public LocalDate obtenerEstado() {
        return estado;
    }

    public void establecerEstado(LocalDate estado) {
        this.estado = estado;
    }
}

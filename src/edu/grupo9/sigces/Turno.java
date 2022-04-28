package edu.grupo9.sigces;

import java.time.LocalDate;

public class Turno {

    private String idTurno;
    private String idPaciente;
    private String idAgenda;
    private LocalDate diaTurno;
    private LocalDate horaTurno;


    public Turno() {
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(String idAgenda) {
        this.idAgenda = idAgenda;
    }

    public LocalDate getDiaTurno() {
        return diaTurno;
    }

    public void setDiaTurno(LocalDate diaTurno) {
        this.diaTurno = diaTurno;
    }

    public LocalDate getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalDate horaTurno) {
        this.horaTurno = horaTurno;
    }
}

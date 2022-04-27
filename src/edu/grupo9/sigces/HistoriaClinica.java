package edu.grupo9.sigces;

public class HistoriaClinica {

    final String centroDeSalud = "Clínica San Camilo";
    private String idHistoriaClinica;
    private String idPaciente;

    public HistoriaClinica() {
    }

    public String getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(String idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
}

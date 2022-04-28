package edu.grupo9.sigces;

public class HistoriaClinica {

    final String CENTRODESALUD = "Clínica San Camilo";
    private String idHistoriaClinica;
    private String idPaciente;

    public HistoriaClinica() { }

    public String obtenerIdHistoriaClinica() { return idHistoriaClinica; }

    public void establecerIdHistoriaClinica(String idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public String obtenerIdPaciente() { return idPaciente; }

    public void establecerIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
}

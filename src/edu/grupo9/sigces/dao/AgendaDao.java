package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.objects.Sesion;
import org.threeten.extra.Interval;

import java.time.LocalDate;

public interface AgendaDao {
    void buscarAgenda(int idMedico, LocalDate diaLaborable);
    Integer buscarIdAgenda();
    void crearAgenda(int idMedico);
    void establecerDiasLaborables(int idMedico, String diasLaborables, Interval intervaloLaborable);
    void gestionarAgenda(Sesion sesion);
}

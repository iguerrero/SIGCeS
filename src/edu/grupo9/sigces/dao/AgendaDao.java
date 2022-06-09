package edu.grupo9.sigces.dao;

import org.threeten.extra.Interval;

public interface AgendaDao {
    void buscarAgenda(int idMedico, String diasLaborables);
    void establecerDiasLaborables(int idMedico, String diasLaborables, Interval intervaloLaborable);
}

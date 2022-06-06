package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Agenda;
import org.threeten.extra.Interval;

import java.time.LocalDate;
import java.util.ArrayList;

public interface AgendaDao {
    void buscarAgenda(int idMedico, String diasLaborables);
    void establecerDiasLaborables(int idMedico, String diasLaborables, Interval intervaloLaborable);
}

package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Agenda;
import org.threeten.extra.Interval;

import java.time.LocalDate;
import java.util.ArrayList;

public interface AgendaDao {

    ArrayList<Interval> listarTurnos(int idMedico, LocalDate fecha);
    void establecerDiasLaborables();
}

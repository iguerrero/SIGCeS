package edu.grupo9.sigces.dao;

import org.threeten.extra.Interval;

import java.time.LocalDate;
import java.util.ArrayList;

public interface TurnoDao {
    void generarNuevoTurno();
    void modificarTurno();
    void eliminarTurno();
    ArrayList<Interval> listarTurnos(int idAgenda, LocalDate fecha);
}

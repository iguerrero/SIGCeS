package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.SQLiteDB;
import org.threeten.extra.Interval;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaDaoImpl extends SQLiteDB implements AgendaDao {

    @Override
    public ArrayList<Interval> listarTurnos(int idMedico, LocalDate fecha) {
        conectar();
        try {
            PreparedStatement agd = connection.prepareStatement("SELECT * FROM agendas WHERE idMedico = ? AND fecha = ?");
            agd.setInt(1, idMedico);
            agd.setObject(2,fecha);
            agd.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return null;
    }

    @Override
    public void establecerDiasLaborables() {

    }
}

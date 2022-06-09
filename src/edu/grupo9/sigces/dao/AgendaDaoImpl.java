package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.SQLiteDB;
import org.threeten.extra.Interval;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendaDaoImpl extends SQLiteDB implements AgendaDao {

    @Override
    public void buscarAgenda(int idMedico, String diaLaborable) {
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FORM agendas WHERE idMedico = ? AND diaLaborable = ?");
            statement.setInt(1, idMedico);
            statement.setString(2, diaLaborable);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void establecerDiasLaborables(int idMedico, String diasLaborables, Interval intervaloLaborable) {
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT IN agendas (idMedico, diasLaborables, intervaloLaborable) VALUES(?, ?, ?)");
            statement.setInt(1, idMedico);
            statement.setString(2, diasLaborables);
            statement.setString(2, intervaloLaborable.toString());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

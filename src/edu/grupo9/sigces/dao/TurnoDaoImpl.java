package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.SQLiteDB;
import edu.grupo9.sigces.Turno;
import org.threeten.extra.Interval;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TurnoDaoImpl extends SQLiteDB implements TurnoDao{

    @Override
    public void generarNuevoTurno() {
        Turno turno = new Turno();
        Scanner scanner = new Scanner(System.in);

    }

    @Override
    public void modificarTurno() {

    }

    @Override
    public void eliminarTurno() {

    }
    public ArrayList<Interval> listarTurnos(int idAgenda, LocalDate fecha) {
        conectar();
        try {
            PreparedStatement agd = connection.prepareStatement("SELECT * FROM turnos WHERE idMedico = ? AND fecha = ?");
            agd.setInt(1, idAgenda);
            agd.setObject(2,fecha);
            agd.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return null;
    }
}

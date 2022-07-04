package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.SQLiteDB;
import edu.grupo9.sigces.objects.Agenda;
import edu.grupo9.sigces.objects.Medico;
import edu.grupo9.sigces.objects.Paciente;
import edu.grupo9.sigces.objects.Turno;
import org.threeten.extra.Interval;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static edu.grupo9.sigces.dao.AgendaDaoImpl.buscarAgendasActivas;

public class TurnoDaoImpl extends SQLiteDB implements TurnoDao{

    @Override
    public void generarNuevoTurno() {
        Turno turno = new Turno();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del paciente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del paciente:");
        String apellido = scanner.nextLine();
        PacienteDao paciente = new PacienteDaoImpl();
        ArrayList<Paciente> pcts = paciente.buscarPacientePorNombre(nombre, apellido);
        for (Paciente pct:pcts) {
            System.out.println(pct.obtenerIdPaciente() + " | " + pct.obtenerApellido() + ", " + pct.obtenerNombre());
        }
        System.out.print("Seleccione el Id del paciente:");
        int idPaciente = scanner.nextInt();

        MedicoDao medico = new MedicoDaoImpl();
        ArrayList<Medico> mdcs = medico.buscarMedicosPorEspecialidad();
        for (Medico mdc:mdcs) {
            System.out.println(mdc.obtenerIdMedico() + " | " + mdc.obtenerApellido() + ", " + mdc.obtenerNombre());
        }
        System.out.print("Seleccione el Id del medico:");
        int idMedico = scanner.nextInt();

        System.out.println("Estas son la fechas disponibles:");
        List<Agenda> fechasDisponibles= buscarAgendasActivas(idMedico);
        for (Agenda fecha:fechasDisponibles){
            System.out.println(fecha.obtenerFecha());
        }

        System.out.println("Ingrese la fecha deseada (AAAA-MM-DD)");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());


    }

    @Override
    public void modificarTurno() { }

    @Override
    public void eliminarTurno() { }

    @Override
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

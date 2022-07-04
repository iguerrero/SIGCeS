/* Esta clase trabaja sobre la clase Agenda. Una agenda, en este caso,
   representa solamente un día laborable y el intervalo en el que trabaja.
   Cada médico puede generar tantas agendas como días trabaje.
 */
package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.SQLiteDB;
import edu.grupo9.sigces.objects.Agenda;
import edu.grupo9.sigces.objects.Medico;
import edu.grupo9.sigces.objects.Sesion;
import org.threeten.extra.Interval;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.grupo9.sigces.Main.gestionarMedicos;
import static edu.grupo9.sigces.Main.menuAdmin;
import static edu.grupo9.sigces.Utilidades.seleccion;

public class AgendaDaoImpl extends SQLiteDB implements AgendaDao {

    /**
     * Devuelve todas las fechas disponibles para un determinado médico.
     * @param idMedico
     * @return agendas
     */
    public static ArrayList<Agenda> buscarAgendasActivas(int idMedico) {
        ArrayList<Agenda> agendas = null;
        try {
            conectar();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM agendas WHERE idMedico =?;");
            statement.setInt(1, idMedico);
            ResultSet agds = statement.executeQuery();
            if (agds != null) {
                while (agds.next()) {
                    Agenda agenda = new Agenda(agds.getInt(1), LocalDate.parse(agds.getString(2)), Interval.parse(agds.getString(3)));
                    agendas.add(agenda);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return agendas;
    }

    @Override
    public void buscarAgenda(int idMedico, LocalDate diaLaborable) {
        try {
            conectar();
            PreparedStatement statement = connection.prepareStatement("SELECT * FORM agendas WHERE idMedico = ? AND diaLaborable = ?");
            statement.setInt(1, idMedico);
            statement.setDate(2, Date.valueOf(diaLaborable));
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recibe nombre y apellido del médico solicitado y devuelve el ID de su agenda.
     * @return
     */
    public Integer buscarIdAgenda() {
        System.out.println("Ingrese el nombre y apellido del médico:");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        try {
            conectar();
            PreparedStatement statement = connection.prepareStatement("SELECT idMedico FORM medicos WHERE nombre = ? AND apellido = ?");
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            ResultSet med = statement.executeQuery();
            int idMedico = 0;
            if (med != null) {
                idMedico = med.getInt(1);
            }

            statement = connection.prepareStatement("SELECT idAgenda FORM agendas WHERE idMedico = ? AND diaLaborable = ?");
            statement.setInt(1, idMedico);
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet res = statement.executeQuery();
            return res.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Crea una nueva agenda para una fecha determinada.
     * @param idMedico
     */
    @Override
    public void crearAgenda(int idMedico) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("hh:mm");
        System.out.println("Ingrese la fecha (DD/MM/AAAA)");
        LocalDate fecha = LocalDate.parse(scanner.next(), formatDate);
        System.out.println("Ingrese la hora de inicio de la actividad (HH:MM)");
        LocalTime horaIni = LocalTime.parse(scanner.next(), formatTime);
        System.out.println("Ingrese la hora de finalización (HH:MM)");
        LocalTime horaFin = LocalTime.parse(scanner.next(), formatTime);
        LocalDateTime instanteInicial = LocalDateTime.of(fecha, horaIni);
        LocalDateTime instanteFinal = LocalDateTime.of(fecha, horaFin);
        Interval intervaloLaborable = Interval.of(Instant.parse(instanteInicial.toString()), Instant.parse(instanteFinal.toString()));
    }

    @Override
    public void establecerDiasLaborables(int idMedico, String diasLaborables, Interval intervaloLaborable) {

        try {
            conectar();
            PreparedStatement statement = connection.prepareStatement("INSERT IN agendas (idMedico, diasLaborables, intervaloLaborable) VALUES(?, ?, ?)");
            statement.setInt(1, idMedico);
            statement.setString(2, diasLaborables);
            statement.setString(2, intervaloLaborable.toString());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void gestionarAgenda(Sesion sesion) {
        Scanner scanner = new Scanner(System.in);
        MedicoDao med = new MedicoDaoImpl();
        Medico medico = null;
        System.out.println("""
                Seleccione un médico: \s
                1. por nombre y apellido \s
                2. por Id \s
                """);
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                medico = med.buscarMedicosPorNombre(nombre, apellido);
            }
            case 2 -> {
                System.out.print("Id: ");
                int id = scanner.nextInt();
                medico = med.buscarMedicoPorId(id);
            }
        }
        System.out.println("Usted seleccionó a: " + medico.toString());
        System.out.println("""
                ¿Qué desea hacer? \s
                1. Buscar agenda de hoy \s
                2. Modificar Agenda \s
                3. Eliminar Agenda \s
                4. Generar nueva Agenda \s
                0. Volver al menú principal \s
                """);
        switch (seleccion()) {
            case 1 -> { buscarAgenda(medico.obtenerIdMedico(), LocalDate.now()); gestionarMedicos(sesion);}
            case 2 -> {}
            case 3 -> {}
            case 4 -> { crearAgenda(medico.obtenerIdMedico()); gestionarMedicos(sesion); }
            case 0 -> menuAdmin(sesion);
        }
    }

}

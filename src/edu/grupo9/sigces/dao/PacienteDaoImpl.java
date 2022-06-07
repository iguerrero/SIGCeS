package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Paciente;
import edu.grupo9.sigces.SQLiteDB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static edu.grupo9.sigces.Utilidades.*;

public class PacienteDaoImpl extends SQLiteDB implements PacienteDao{
    private Paciente paciente;
    @Override
    public void cargarNuevo() {
        System.out.println("Por favor, ingrese los datos del nuevo Paciente.");
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("DNI: ");
        int dni = sc.nextInt();
        sc.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("eMail: ");
        String eMail = sc.nextLine();
        System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
        LocalDate fechaNac = LocalDate.parse(sc.nextLine(), formatter);
        System.out.print("Sexo: ");
        String sexo = sc.nextLine();
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("INSERT INTO pacientes (" +
                    "nombre, " +
                    "apellido, " +
                    "dni, " +
                    "domicilio, " +
                    "telefono, " +
                    "email, " +
                    "fechaNac, " +
                    "sexo)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            st.setString(1, nombre);
            st.setString(2, apellido);
            st.setInt(3, dni);
            st.setString(4, domicilio);
            st.setString(5, telefono);
            st.setString(6, eMail);
            st.setObject(7, fechaNac);
            st.setString(8, sexo);
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        System.out.println("El paciente ha sido agregado con éxito.");
        dormirPor(2000);
        limpiarPantalla();
    }

    @Override
    public List<Paciente> listarTodos() {
        ArrayList<Paciente> lista = null;
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM pacientes");
            lista = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Paciente pct = new Paciente();
                pct.establecerIdPaciente(rs.getInt("idPaciente"));
                pct.establecerNombre(rs.getString("nombre"));
                pct.establecerApellido(rs.getString("apellido"));
                pct.establecerDni(rs.getInt("dni"));
                pct.establecerDomicilio(rs.getString("domicilio"));
                pct.establecerTelefono(rs.getString("telefono"));
                pct.establecerEmail(rs.getString("email"));
                pct.establecerFechaNac((LocalDate) rs.getObject("fechaNac"));
                pct.establecerSexo(rs.getString("nombre"));
                lista.add(pct);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return lista;
    }

    @Override
    public void modificar(Paciente paciente) {
        System.out.println("""
                            Por favor, seleccione qué dato desea modificar.\s
                            1. Nombre
                            2. Apellido
                            3. DNI
                            4. Domicilio
                            5. Teléfono
                            6. eMail
                            7. Fecha de Nacimiento
                            8. Sexo
                            """);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        switch (seleccion()) {
            case 1 -> {
                System.out.print("Nombre: ");
                paciente.establecerNombre(scanner.nextLine());
            }
            case 2 -> {
                System.out.print("Apellido: ");
                paciente.establecerApellido(scanner.nextLine());
            }
            case 3 -> {
                System.out.print("DNI: ");
                paciente.establecerDni(scanner.nextInt());
            }
            case 4 -> {
                System.out.print("Domicilio: ");
                paciente.establecerDomicilio(scanner.nextLine());
            }
            case 5 -> {
                System.out.print("Teléfono: ");
                paciente.establecerTelefono(scanner.nextLine());
            }
            case 6 -> {
                System.out.print("eMail: ");
                paciente.establecerEmail(scanner.nextLine());
            }
            case 7 -> {
                System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
                paciente.establecerFechaNac(LocalDate.parse(scanner.nextLine(), formatter));
            }
            case 8 -> {
                System.out.print("Sexo: ");
                paciente.establecerSexo(scanner.nextLine());
            }
        }
        System.out.println("""
                            ¿Desea seguir modificando? \s
                            1. Sí. Deseo hacer otra modificación.
                            2. No. Deseo guardar las modificaciones.
                            """);
        if (seleccion() == 1) { modificar(paciente); }
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("UPDATE pacientes " +
                    "SET nombre = ?, " +
                    "apellido = ?, " +
                    "dni = ?, " +
                    "domicilio = ?, " +
                    "telefono = ?, " +
                    "email = ?, " +
                    "fechaNac = ?, " +
                    "sexo = ? " +
                    "WHERE idPaciente = ?;");
            st.setString(1, paciente.obtenerNombre());
            st.setString(2, paciente.obtenerApellido());
            st.setInt(3, paciente.obtenerDni());
            st.setString(4, paciente.obtenerDomicilio());
            st.setString(5, paciente.obtenerTelefono());
            st.setString(6, paciente.obtenerEmail());
            st.setObject(7, paciente.obtenerFechaNac());
            st.setString(8, String.valueOf(paciente.obtenerSexo()));
            st.setInt(9, paciente.obtenerIdPaciente());
            st.executeUpdate();
            System.out.print("Los datos del paciente han sido actualizados.");
            dormirPor(2000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public void borrar(Paciente paciente) {
        System.out.println("Usted está a punto de eliminar el registro de:");
        imprimirPaciente(paciente);
        System.out.println("Por favor, confirme (S/N)");
        Scanner scanner = new Scanner(System.in);
        String siono = scanner.nextLine();
        switch (siono.toUpperCase()) {
            case "S" -> {
                try {
                    conectar();
                    PreparedStatement st = connection.prepareStatement("DELETE FROM pacientes WHERE idPaciente = ?;");
                    st.setInt(1, paciente.obtenerIdPaciente());
                    st.executeUpdate();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                } finally {
                    cerrarConexion();
                    System.out.println("El registro ha sido borrado.");
                }
            }
            case "N" -> {

            }
        }
    }

    @Override
    public ArrayList<Paciente> buscarPacientePorNombre(String nombre, String apellido) {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        conectar();
        ResultSet res = null;
        int id = 0;
        try {
            PreparedStatement pct = connection.prepareStatement("SELECT * FROM pacientes WHERE nombre = ? AND apellido = ?");
            pct.setString(1, nombre);
            pct.setString(2, apellido);
            res = pct.executeQuery();
            while (res.next()) {
                Paciente paciente = new Paciente(res.getString(2),
                        res.getString(3),
                        res.getInt(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7),
                        Date.valueOf(res.getObject(8).toString()).toLocalDate(),
                        res.getString(9));
                paciente.establecerIdPaciente(res.getInt(1));
                pacientes.add(paciente);
//                imprimirResultSet(res);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return pacientes;
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        conectar();
        ResultSet res;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            PreparedStatement pct = connection.prepareStatement("SELECT * FROM pacientes WHERE idPaciente = ?");
            pct.setInt(1, id);
            res = pct.executeQuery();
            if (res != null) {
                paciente = new Paciente(res.getString(2),
                        res.getString(3),
                        res.getInt(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7),
                        Date.valueOf(res.getObject(8).toString()).toLocalDate(),
                        res.getString(9));
                paciente.establecerIdPaciente(id);
//                imprimirResultSet(res);
            } else {
                System.out.println("Paciente inexistente");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return paciente;
    }

    public void imprimirPaciente(Paciente paciente) {
        System.out.print(paciente.obtenerIdPaciente() + " | " +
                paciente.obtenerNombre() + " | " +
                paciente.obtenerApellido() + " | " +
                paciente.obtenerDni() + " | " +
                paciente.obtenerDomicilio() + " | " +
                paciente.obtenerTelefono() + " | " +
                paciente.obtenerEmail() + " | " +
                paciente.obtenerFechaNac() + " | " +
                paciente.obtenerSexo());
        System.out.println("");
    }
}

package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Admin;
import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.SQLiteDB;
import edu.grupo9.sigces.Turno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static edu.grupo9.sigces.Utilidades.validarClave;

public class AdminDaoImpl extends SQLiteDB implements AdminDao {

    @Override
    public void cargarNuevo(Admin admin) {
        System.out.println("Por favor, ingrese los datos del nuevo Administrador.");
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.print("Nombre: ");
        admin.establecerNombre(scanner.nextLine());
        System.out.print("Apellido: ");
        admin.establecerApellido(scanner.nextLine());
        System.out.print("DNI: ");
        admin.establecerDni(scanner.nextInt());
        String claveValida;
        while (!validarClave(claveValida = scanner.nextLine())) {
            System.out.println("Clave (debe tener 4 dígitos):");
        }
        admin.establecerClave(claveValida);
        System.out.print("Domicilio: ");
        admin.establecerDomicilio(scanner.nextLine());
        System.out.print("Teléfono: ");
        admin.establecerTelefono(scanner.nextLine());
        System.out.print("eMail: ");
        admin.establecerEmail(scanner.nextLine());
        System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
        admin.establecerFechaNac(LocalDate.parse(scanner.nextLine(), formatter));
        System.out.print("Sexo: ");
        admin.establecerSexo(scanner.nextLine().charAt(0));
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("INSERT INTO admins (" +
                    "nombre, " +
                    "apellido, " +
                    "dni, " +
                    "clave, " +
                    "domicilio, " +
                    "telefono, " +
                    "email, " +
                    "fechaNac, " +
                    "sexo)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, admin.obtenerNombre());
            st.setString(2, admin.obtenerApellido());
            st.setInt(3, admin.obtenerDni());
            st.setString(4, admin.obtenerClave());
            st.setString(5, admin.obtenerDomicilio());
            st.setString(6, admin.obtenerTelefono());
            st.setString(7, admin.obtenerEmail());
            st.setObject(8, admin.obtenerFechaNac());
            st.setString(9, String.valueOf(admin.obtenerSexo()));

            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public List<Admin> listarTodos() {
        List<Admin> lista = null;
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM admins");
            lista = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Admin adm = new Admin();
                adm.establecerIdAdmin(rs.getInt("idAdmin"));
                adm.establecerNombre(rs.getString("nombre"));
                adm.establecerApellido(rs.getString("apellido"));
                adm.establecerDni(rs.getInt("dni"));
                adm.establecerDomicilio(rs.getString("domicilio"));
                adm.establecerTelefono(rs.getString("telefono"));
                adm.establecerEmail(rs.getString("email"));
                adm.establecerFechaNac((LocalDate) rs.getObject("fechaNac"));
                adm.establecerSexo(rs.getString("nombre").charAt(0));
                lista.add(adm);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return lista;
    }

    @Override
    public void modificar(Admin admin) {

    }

    @Override
    public void borrar(Admin admin) {
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("DELETE FROM admins WHERE nombre = ? AND apellido = ?");
            st.setString(1, admin.obtenerNombre());
            st.setString(2, admin.obtenerApellido());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public void generarTurno(Turno turno) {

    }

    @Override
    public void borraTurno(Turno turno) {

    }
}

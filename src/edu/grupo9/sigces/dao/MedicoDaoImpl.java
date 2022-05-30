package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.SQLiteDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static edu.grupo9.sigces.Utilidades.validarClave;

public class MedicoDaoImpl extends SQLiteDB implements MedicoDao {
    @Override
    public void cargarNuevo(Medico medico) {
        System.out.println("Por favor, ingrese los datos del nuevo Médico.");
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.print("Nombre: ");
        medico.establecerNombre(scanner.nextLine());
        System.out.print("Apellido: ");
        medico.establecerApellido(scanner.nextLine());
        System.out.print("DNI: ");
        medico.establecerDni(scanner.nextInt());
        String claveValida;
        while (!validarClave(claveValida = scanner.nextLine())) {
            System.out.println("Clave (debe tener 4 dígitos):");
        }
        medico.establecerClave(claveValida);
        System.out.print("Domicilio: ");
        medico.establecerDomicilio(scanner.nextLine());
        System.out.print("Teléfono: ");
        medico.establecerTelefono(scanner.nextLine());
        System.out.print("eMail: ");
        medico.establecerEmail(scanner.nextLine());
        System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
        medico.establecerFechaNac(LocalDate.parse(scanner.nextLine(), formatter));
        System.out.print("Sexo: ");
        medico.establecerSexo(scanner.nextLine().charAt(0));
        System.out.print("Matrícula Prov.: ");
        medico.establecerMatriculaProv(scanner.nextLine());
        System.out.print("Matrícula Nac.: ");
        medico.establecerMatriculaNac(scanner.nextLine());
        System.out.print("Especialidades (Separadas por coma): ");
        medico.establecerEspecialidades(scanner.nextLine());
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("INSERT INTO medicos (" +
                    "nombre, " +
                    "apellido, " +
                    "dni, " +
                    "clave, " +
                    "domicilio, " +
                    "telefono, " +
                    "email, " +
                    "fechaNac, " +
                    "sexo, " +
                    "matriculaProv, " +
                    "matriculaNac, " +
                    "especialidades)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, medico.obtenerNombre());
            st.setString(2, medico.obtenerApellido());
            st.setInt(3, medico.obtenerDni());
            st.setString(4, medico.obtenerClave());
            st.setString(5, medico.obtenerDomicilio());
            st.setString(6, medico.obtenerTelefono());
            st.setString(7, medico.obtenerEmail());
            st.setObject(8, medico.obtenerFechaNac());
            st.setString(9, String.valueOf(medico.obtenerSexo()));
            st.setString(10, medico.obtenerMatriculaProv());
            st.setString(11, medico.obtenerMatriculaNac());
            st.setString(12, medico.obtenerEspecialidades());
            st.executeUpdate();
//                    'René', 'Favaloro', 4567890, '1234', 'Av. Belgrano 1746', '011 43781200', " +
//                    "'rene@favaloro.com', 1928-07-12, 'M', '11111', '11111', 'Cardiología')");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public List<Medico> listarTodos() {
        List<Medico> lista = null;
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM medicos");
            lista = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Medico med = new Medico();
                med.establecerIdMedico(rs.getInt("idMedico"));
                med.establecerNombre(rs.getString("nombre"));
                med.establecerApellido(rs.getString("apellido"));
                med.establecerDni(rs.getInt("dni"));
                med.establecerDomicilio(rs.getString("domicilio"));
                med.establecerTelefono(rs.getString("telefono"));
                med.establecerEmail(rs.getString("email"));
                med.establecerFechaNac((LocalDate) rs.getObject("fechaNac"));
                med.establecerSexo(rs.getString("nombre").charAt(0));
                med.establecerMatriculaProv(rs.getString("matriculaProv"));
                med.establecerMatriculaNac(rs.getString("matriculaNac"));
                med.establecerEspecialidades(rs.getString("especialidades"));
                lista.add(med);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return lista;
    }

    @Override
    public void modificar(Medico medico) {
    }

    @Override
    public void borrar(Medico medico) {
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("DELETE FROM medicos WHERE nombre = ? AND apellido = ?");
            st.setString(1, medico.obtenerNombre());
            st.setString(2, medico.obtenerApellido());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
}

package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.Main;
import edu.grupo9.sigces.Medico;
import edu.grupo9.sigces.SQLiteDB;
import edu.grupo9.sigces.Sesion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.grupo9.sigces.Utilidades.seleccion;
import static edu.grupo9.sigces.Utilidades.validarClave;

public class MedicoDaoImpl extends SQLiteDB implements MedicoDao {

    private Medico medico;

    @Override
    public void cargarNuevo() {
        System.out.println("Por favor, ingrese los datos del nuevo Médico.");
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        String claveValida;
        while (!validarClave(claveValida = scanner.nextLine())) {
            System.out.println("Clave (debe tener 4 dígitos):");
        }
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("eMail: ");
        String eMail = scanner.nextLine();
        System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
        LocalDate fechaNac = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Matrícula Prov.: ");
        String matriculaProv = scanner.nextLine();
        System.out.print("Matrícula Nac.: ");
        String matriculaNac = scanner.nextLine();
        System.out.print("Especialidades (Separadas por coma): ");
        String especialidades = scanner.nextLine();
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
            st.setString(1, nombre);
            st.setString(2, apellido);
            st.setInt(3, dni);
            st.setString(4, claveValida);
            st.setString(5, domicilio);
            st.setString(6, telefono);
            st.setString(7, eMail);
            st.setObject(8, fechaNac);
            st.setString(9, sexo);
            st.setString(10, matriculaProv);
            st.setString(11, matriculaNac);
            st.setString(12, especialidades);
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
        System.out.println("El médico ha sido agregado con éxito.");
    }

    @Override
    public ArrayList<Medico> listarTodos() {
        ArrayList<Medico> lista = null;
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
                med.establecerSexo(rs.getString("nombre"));
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
        System.out.println("""
                            Por favor, seleccione qué dato desea modificar.\s
                            1. Nombre
                            2. Apellido
                            3. DNI
                            4. Clave
                            5. Domicilio
                            6. Teléfono
                            7. eMail
                            8. Fecha de Nacimiento
                            9. Sexo
                            10. Matrícula Provincial
                            11. Matrícula Nacional
                            12. Especialidades
                            """);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        switch (seleccion()) {
            case 1: {
                System.out.print("Nombre: ");
                medico.establecerNombre(scanner.nextLine());
            }
            case 2: {
                System.out.print("Apellido: ");
                medico.establecerApellido(scanner.nextLine());
            }
            case 3: {
                System.out.print("DNI: ");
                medico.establecerDni(scanner.nextInt());
            }
            case 4: {
                String claveValida;
                while (!validarClave(claveValida = scanner.nextLine())) {
                    System.out.println("Clave (debe tener 4 dígitos):");
                }
                medico.establecerClave(claveValida);
            }
            case 5: {
                System.out.print("Domicilio: ");
                medico.establecerDomicilio(scanner.nextLine());
            }
            case 6: {
                System.out.print("Teléfono: ");
                medico.establecerTelefono(scanner.nextLine());
            }
            case 7: {
                System.out.print("eMail: ");
                medico.establecerEmail(scanner.nextLine());
            }
            case 8: {
                System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
                medico.establecerFechaNac(LocalDate.parse(scanner.nextLine(), formatter));
            }
            case 9: {
                System.out.print("Sexo: ");
                medico.establecerSexo(scanner.nextLine());
            }
            case 10: {
                System.out.print("Matrícula Prov.: ");
                medico.establecerMatriculaProv(scanner.nextLine());
            }
            case 11: {
                System.out.print("Matrícula Nac.: ");
                medico.establecerMatriculaNac(scanner.nextLine());
            }
            case 12: {
                System.out.print("Especialidades (Separadas por coma): ");
                medico.establecerEspecialidades(scanner.nextLine());
            }
        }
        System.out.println("""
                            ¿Desea seguir modificando? \s
                            1. Sí. Deseo hacer otra modificación.
                            2. No. Deseo guardar las modificaciones.
                            """);
        if (seleccion() == 2) { modificar(medico); }
        scanner.close();
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("UPDATE medicos" +
                    "SET nombre = ?, " +
                    "apellido = ?, " +
                    "dni = ?, " +
                    "clave = ?, " +
                    "domicilio = ?, " +
                    "telefono = ?, " +
                    "email = ?, " +
                    "fechaNac = ?, " +
                    "sexo = ?, " +
                    "matriculaProv = ?, " +
                    "matriculaNac = ?, " +
                    "especialidades = ?)" +
                    "WHERE idMedico = ?");
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
            st.setInt(13, medico.obtenerIdMedico());
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
    public void borrar(Medico medico) {
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("DELETE FROM medicos WHERE idMedico = ?");
            st.setInt(1, medico.obtenerIdMedico());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public ArrayList<Medico> buscarMedicosPorNombre(String nombre, String apellido) {
        ArrayList<Medico> medicos = null;
        conectar();
        ResultSet res = null;
        int id = 0;
        try {
            PreparedStatement med = connection.prepareStatement("SELECT * FROM medicos WHERE nombre = ? AND apellido = ?");
            med.setString(1, nombre);
            med.setString(2, apellido);
            med.executeQuery();
            cerrarConexion();
            res = med.getResultSet();
            if (res != null) {
                while (res.next()) {
                    medico = new Medico(res.getInt(1),
                            res.getString(2),
                            res.getString(3),
                            res.getInt(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            (LocalDate) res.getObject(9),
                            res.getString(10),
                            res.getString(11),
                            res.getString(12),
                            res.getString(13));
                    medicos.add(medico);
                }
            } else {
                System.out.println("Médico inexistente");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return medicos;
    }

    @Override
    public ArrayList<Medico> buscarMedicosPorEspecialidad() {
        ArrayList<Medico> medicos = null;
        System.out.println("Ingrese la especialidad");
        Scanner sc = new Scanner(System.in);
        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();
        sc.close();
        conectar();
        ResultSet res = null;
        int id = 0;
        try {
            PreparedStatement med = connection.prepareStatement("SELECT * FROM medicos WHERE especialidades = ?");
            med.setString(1, especialidad);
            med.executeQuery();
            cerrarConexion();
            res = med.getResultSet();
            if (res != null) {
                while (res.next()) {
                    medico = new Medico(res.getInt(1),
                            res.getString(2),
                            res.getString(3),
                            res.getInt(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            (LocalDate) res.getObject(9),
                            res.getString(10),
                            res.getString(11),
                            res.getString(12),
                            res.getString(13));
                    medicos.add(medico);
                }
            } else {
                System.out.println("No hay médicos para esta especialidad.");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return medicos;
    }

    @Override
    public Medico buscarMedicoPorId(int id) {
        conectar();
        ResultSet res;
        try {
            PreparedStatement med = connection.prepareStatement("SELECT * FROM medicos WHERE idMedico = ?");
            med.setInt(1, id);
            med.executeQuery();
            cerrarConexion();
            res = med.getResultSet();
            if (res != null) {
                medico = new Medico(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getInt(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7),
                        res.getString(8),
                        (LocalDate) res.getObject(9),
                        res.getString(10),
                        res.getString(11),
                        res.getString(12),
                        res.getString(13));
            } else {
                System.out.println("Médico inexistente");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return medico;
    }

    @Override
    public Sesion loginMedico(int intento) {
        Sesion sesion = null;
        System.out.println("Ingrese su usuario y contraseña.");
        Scanner sc = new Scanner(System.in);
        System.out.print("DNI: ");
        int dni = sc.nextInt();
        System.out.print("Contraseña: ");
        String clave = sc.nextLine();
        sc.close();
        conectar();
        ResultSet res;
        try {
            PreparedStatement med = connection.prepareStatement("SELECT * FROM medicos WHERE dni = ? AND clave = ?");
            med.setInt(1, dni);
            med.setString(2, clave);
            res = med.executeQuery();
            if (res != null) {
                sesion = new Sesion(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        "Médico",
                        res.getString(10));
            } else if (intento > 2){
                System.out.println("Usuario o contraseña no coinciden. Vuelva a intentarlo.");
                loginMedico(intento++);
            } else {
                Main.bienvenida();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return sesion;
    }
}
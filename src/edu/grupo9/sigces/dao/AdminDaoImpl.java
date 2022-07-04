package edu.grupo9.sigces.dao;

import edu.grupo9.sigces.*;
import edu.grupo9.sigces.objects.Admin;
import edu.grupo9.sigces.objects.Sesion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static edu.grupo9.sigces.Utilidades.seleccion;
import static edu.grupo9.sigces.Utilidades.validarClave;

public class AdminDaoImpl extends SQLiteDB implements AdminDao {

    private Admin admin;

    @Override
    public void cargarNuevo() {
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
        admin.establecerSexo(scanner.nextLine());
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
                adm.establecerSexo(rs.getString("nombre"));
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
                            """);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        switch (seleccion()) {
            case 1: {
                System.out.print("Nombre: ");
                admin.establecerNombre(scanner.nextLine());
            }
            case 2: {
                System.out.print("Apellido: ");
                admin.establecerApellido(scanner.nextLine());
            }
            case 3: {
                System.out.print("DNI: ");
                admin.establecerDni(scanner.nextInt());
            }
            case 4: {
                String claveValida;
                while (!validarClave(claveValida = scanner.nextLine())) {
                    System.out.println("Clave (debe tener 4 dígitos):");
                }
                admin.establecerClave(claveValida);
            }
            case 5: {
                System.out.print("Domicilio: ");
                admin.establecerDomicilio(scanner.nextLine());
            }
            case 6: {
                System.out.print("Teléfono: ");
                admin.establecerTelefono(scanner.nextLine());
            }
            case 7: {
                System.out.print("eMail: ");
                admin.establecerEmail(scanner.nextLine());
            }
            case 8: {
                System.out.print("Fecha de Nacimiento (DD/MM/YYYY): ");
                admin.establecerFechaNac(LocalDate.parse(scanner.nextLine(), formatter));
            }
            case 9: {
                System.out.print("Sexo: ");
                admin.establecerSexo(scanner.nextLine());
            }
        }
        System.out.println("""
                            ¿Desea seguir modificando? \s
                            1. Sí. Deseo hacer otra modificación.
                            2. No. Deseo guardar las modificaciones.
                            """);
        if (seleccion() == 2) { modificar(admin); }
        scanner.close();
        try {
            conectar();
            PreparedStatement st = connection.prepareStatement("UPDATE admins" +
                    "SET nombre = ?, " +
                    "apellido = ?, " +
                    "dni = ?, " +
                    "clave = ?, " +
                    "domicilio = ?, " +
                    "telefono = ?, " +
                    "email = ?, " +
                    "fechaNac = ?, " +
                    "sexo = ?, " +
                    "WHERE idMedico = ?;");
            st.setString(1, admin.obtenerNombre());
            st.setString(2, admin.obtenerApellido());
            st.setInt(3, admin.obtenerDni());
            st.setString(4, admin.obtenerClave());
            st.setString(5, admin.obtenerDomicilio());
            st.setString(6, admin.obtenerTelefono());
            st.setString(7, admin.obtenerEmail());
            st.setObject(8, admin.obtenerFechaNac());
            st.setString(9, String.valueOf(admin.obtenerSexo()));
            st.setInt(13, admin.obtenerIdAdmin());
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
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
    public ArrayList<Admin> buscarAdminsPorNombre(String nombre, String apellido) {
        ArrayList<Admin> admins = null;
        System.out.println("Ingrese el nombre y apellido del Administrador");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Apellido: ");
        apellido = sc.nextLine();
        sc.close();
        conectar();
        ResultSet res;
        try {
            PreparedStatement med = connection.prepareStatement("SELECT * FROM admins WHERE nombre = ? AND apellido = ?");
            med.setString(1, nombre);
            med.setString(2, apellido);
            med.executeQuery();
            cerrarConexion();
            res = med.getResultSet();
            if (res != null) {
                while (res.next()) {
                    admin = new Admin(res.getInt(1),
                            res.getString(2),
                            res.getString(3),
                            res.getInt(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            (LocalDate) res.getObject(9),
                            res.getString(10));
                    admins.add(admin);
                }
            } else {
                System.out.println("Administrador inexistente");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return admins;
    }

    @Override
    public Admin buscarAdminPorId(int id) {
        conectar();
        ResultSet res;
        try {
            PreparedStatement adm = connection.prepareStatement("SELECT * FROM admins WHERE idAdmin = ?");
            adm.setInt(1, id);
            adm.executeQuery();
            cerrarConexion();
            res = adm.getResultSet();
            if (res != null) {
                admin = new Admin(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getInt(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7),
                        res.getString(8),
                        (LocalDate) res.getObject(9),
                        res.getString(10));
            } else {
                System.out.println("Administrador inexistente");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        cerrarConexion();
        return admin;
    }

    @Override
    public Sesion loginAdmin(int intento) {
        Sesion sesion = null;
        System.out.println("Ingrese su usuario y contraseña.");
        Scanner sc = new Scanner(System.in);
        System.out.print("DNI: ");
        int dni = sc.nextInt();
        sc.nextLine(); // Esto es debido a un bug.
        System.out.print("Contraseña: ");
        String clave = sc.nextLine();
//        sc.close();
        conectar();
        ResultSet res;
        try {
            PreparedStatement med = connection.prepareStatement("SELECT * FROM admins WHERE dni = ? AND clave = ?;");
            med.setInt(1, dni);
            med.setString(2, clave);
            res = med.executeQuery();
            if (res.next()) {
                sesion = new Sesion(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        "Admin",
                        res.getString(10));
            } else if (res.wasNull() && intento > 2){
                System.out.println("Usuario o contraseña no coinciden. Vuelva a intentarlo.");
                loginAdmin(intento++);
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

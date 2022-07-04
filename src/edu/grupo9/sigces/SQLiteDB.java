package edu.grupo9.sigces;

import java.io.File;
import java.sql.*;

import static edu.grupo9.sigces.Utilidades.limpiarPantalla;

public class SQLiteDB {
    protected static Connection connection = null;

    public static void conectar() {
        try {
            // create a database connection
            Class.forName("org.sqlite.JDBC");
            File dbFile = new File("src\\sigces.db");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());

        } catch (Exception e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    public static void cerrarConexion() {
        try {
            if (connection != null) {connection.close();}
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    public static void crearTablas() {
        try{
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS pacientes (\n" +
                    "  idPaciente INTEGER PRIMARY KEY," +
                    "  nombre VARCHAR(50) NOT NULL," +
                    "  apellido VARCHAR(50) NOT NULL," +
                    "  dni INT NOT NULL UNIQUE," +
                    "  domicilio VARCHAR(50) NOT NULL," +
                    "  telefono VARCHAR(50) NOT NULL," +
                    "  email VARCHAR(50) NOT NULL UNIQUE," +
                    "  fechaNac DATE NOT NULL, " +
                    "  sexo CHAR(1) DEFAULT 'M'" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS medicos (" +
                    "  idMedico INTEGER PRIMARY KEY," +
                    "  nombre VARCHAR(50) NOT NULL," +
                    "  apellido VARCHAR(50) NOT NULL," +
                    "  dni INT NOT NULL UNIQUE," +
                    "  clave VARCHAR(32) NOT NULL," +
                    "  domicilio VARCHAR(50) NOT NULL," +
                    "  telefono VARCHAR(50) NOT NULL," +
                    "  email VARCHAR(50) NOT NULL UNIQUE," +
                    "  fechaNac DATE NOT NULL," +
                    "  sexo CHAR(1) DEFAULT 'M'," +
                    "  matriculaProv VARCHAR(8) NOT NULL UNIQUE," +
                    "  matriculaNac VARCHAR(8) NOT NULL UNIQUE," +
                    "  especialidades VARCHAR(50) NOT NULL" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS admins (\n" +
                    "  idAdmin INTEGER PRIMARY KEY," +
                    "  nombre VARCHAR(50) NOT NULL," +
                    "  apellido VARCHAR(50) NOT NULL," +
                    "  dni INT NOT NULL UNIQUE," +
                    "  clave VARCHAR(32) NOT NULL," +
                    "  domicilio VARCHAR(50) NOT NULL," +
                    "  telefono VARCHAR(50) NOT NULL," +
                    "  email VARCHAR(50) NOT NULL UNIQUE," +
                    "  fechaNac DATE NOT NULL," +
                    "  sexo CHAR(1) DEFAULT 'M'" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS turnos (\n" +
                    "  idTurno INTEGER PRIMARY KEY," +
                    "  timestamp DATE NOT NULL," +
                    "  fecha DATE NOT NULL," +
                    "  hora DATE NOT NULL," +
                    "  estado VARCHAR(15) NOT NULL," +
                    "  idPaciente INTEGER," +
                    "  idAgenda INTEGER NOT NULL" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS agendas (\n" +
                    "  idAgenda INTEGER PRIMARY KEY," +
                    "  idMedico INTEGER NOT NULL," +
                    "  diaLaborable VARCHAR(10)," +
                    "  intervaloLaborable VARCHAR(30)" +
                    ");");

        } catch (Exception e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static void agregarDatos() {
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("INSERT INTO admins (" +
                    "nombre, " +
                    "apellido, " +
                    "dni, " +
                    "clave, " +
                    "domicilio, " +
                    "telefono, " +
                    "email, " +
                    "fechaNac, " +
                    "sexo) " +
                    "VALUES('Alina','Santos', 38111222,'1234', 'Av. 14 125', '3534512345', " +
                    "'alisantos@hotmail.com', 1996-12-12, 'F')");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            cerrarConexion();
        }
    }
    public static void imprimirResultSet(ResultSet rs) throws SQLException    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        limpiarPantalla();
        rs.beforeFirst();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(rs.getString(i));
            }
            System.out.println("");
        }
    }
}

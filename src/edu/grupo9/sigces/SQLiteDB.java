package edu.grupo9.sigces;

import java.io.File;
import java.sql.*;

public class SQLiteDB {

    public static void conectarBaseDeDatos() {
        Connection connection = null;
        try {
            // create a database connection
            Class.forName("org.sqlite.JDBC");
            File dbFile = new File("src\\sigces.db");
            System.out.println(dbFile.getAbsolutePath());
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS pacientes (\n" +
                    "  idPaciente INTEGER PRIMARY KEY," +
                    "  nombre VARCHAR(50) NOT NULL," +
                    "  apellido VARCHAR(50) NOT NULL," +
                    "  dni INT NOT NULL UNIQUE," +
                    "  clave VARCHAR(32) NOT NULL," +
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

            statement.executeUpdate("INSERT INTO medicos (" +
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
                    "VALUES('René', 'Favaloro', 4567890, '1234', 'Av. Belgrano 1746', '011 43781200', " +
                    "'rene@favaloro.com', 1928-07-12, 'M', '11111', '11111', 'Cardiología')");

            ResultSet rs = statement.executeQuery("SELECT * FROM admins");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("nombre"));
                System.out.println("id = " + rs.getInt("idAdmin"));
            }
        } catch (Exception e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}

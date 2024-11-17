package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlite:clinic_system.db";

    // Método para establecer la conexión con la base de datos
    public static Connection connect() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexión establecida a la base de datos.");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            throw e;  
        }
    }

    // Método para inicializar la base de datos y crear tablas si no existen
    public static void initializeDatabase() {
        String createAppointmentsTableSQL = """
            CREATE TABLE IF NOT EXISTS Appointments (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                doctor TEXT NOT NULL,
                datetime TEXT NOT NULL
            );
            """;
        
        String createUsersTableSQL = """
            CREATE TABLE IF NOT EXISTS Users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                email TEXT NOT NULL,
                password TEXT NOT NULL,
                tipoUsuario TEXT NOT NULL
            );
            """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(createAppointmentsTableSQL); // Crear la tabla de turnos si no existe
            stmt.execute(createUsersTableSQL); // Crear la tabla de usuarios si no existe
            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}

package database;

import models.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Método para guardar un turno en la base de datos
    public boolean saveAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointments (doctor, datetime) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, appointment.getDoctor());
            pstmt.setString(2, appointment.getDatetime());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los turnos de la base de datos
    public List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT doctor, datetime FROM Appointments";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String doctor = rs.getString("doctor");
                String datetime = rs.getString("datetime");
                appointments.add(new Appointment(doctor, datetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Método para eliminar un turno por doctor y fecha
    public boolean deleteAppointment(String doctor, String datetime) {
        String sql = "DELETE FROM Appointments WHERE doctor = ? AND datetime = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, doctor);
            pstmt.setString(2, datetime);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Si se eliminó una fila, retorna verdadero
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener los médicos disponibles desde la base de datos
    public List<String> getAvailableDoctors() {
        List<String> doctors = new ArrayList<>();
        String sql = "SELECT nombre FROM Doctors";  
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String doctorName = rs.getString("nombre");
                doctors.add(doctorName);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los médicos: " + e.getMessage());
        }
        return doctors;
    }
}

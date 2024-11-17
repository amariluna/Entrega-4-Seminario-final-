package controllers;

import database.AppointmentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentController {

    @FXML
    private ComboBox<String> doctorComboBox;
    @FXML
    private TextField datetimeField;

    private AppointmentDAO appointmentDAO;

    public AppointmentController() {
        // Instancia de DAO para operaciones de la base de datos
        this.appointmentDAO = new AppointmentDAO();
    }

    @FXML
    public void initialize() {
        // Cargar médicos disponibles de forma dinámica
        loadDoctors();
    }

    private void loadDoctors() {
        // ACargar los médicos desde la base de datos
        List<String> doctors = appointmentDAO.getAvailableDoctors();
        doctorComboBox.getItems().addAll(doctors);
    }

    @FXML
    private void handleRequestAppointment() {
        String selectedDoctor = doctorComboBox.getValue();
        String datetime = datetimeField.getText();

        if (selectedDoctor != null && !datetime.isEmpty()) {
            // Validar que la fecha y hora estén en el formato correcto
            if (isValidDateTime(datetime)) {
                // Crear un nuevo turno y guardarla en la base de datos a través del AppointmentDAO
                Appointment newAppointment = new Appointment(selectedDoctor, datetime);

                if (appointmentDAO.saveAppointment(newAppointment)) {
                    // Mostrar mensaje de confirmación
                    showAlert(Alert.AlertType.INFORMATION, "Turno Solicitado",
                            "Turno solicitado con " + selectedDoctor + " para el " + datetime + ".");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Hubo un error al guardar el turno. Inténtelo nuevamente.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "La fecha/hora no tiene un formato válido. Por favor, intente nuevamente.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Por favor, selecciona un médico y una fecha/hora.");
        }
    }

    private boolean isValidDateTime(String datetime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
            LocalDateTime.parse(datetime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

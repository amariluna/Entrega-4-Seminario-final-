package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Doctor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TableView<Doctor> doctorTable;
    @FXML
    private TableColumn<Doctor, String> nameColumn;
    @FXML
    private TableColumn<Doctor, String> specialtyColumn;

    // Lista de doctores 
    private List<Doctor> doctors = new ArrayList<>();

    @FXML
    public void initialize() {
        // Inicializar columnas de la tabla
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        specialtyColumn.setCellValueFactory(new PropertyValueFactory<>("specialty"));

        // Cargar doctores en la tabla
        loadDoctors();
    }

    private void loadDoctors() {
        // Limpiar la tabla antes de agregar nuevos datos
        doctorTable.getItems().clear();

        // Agregar doctores a la lista (simulado)
        doctors.add(new Doctor("Dr. Juan Pérez", "Cardiología"));
        doctors.add(new Doctor("Dra. María López", "Dermatología"));
        doctors.add(new Doctor("Dr. Carlos Gómez", "Pediatría"));

        // Añadir doctores a la tabla
        doctorTable.getItems().setAll(doctors);
    }

    @FXML
    private void handleRequestAppointment() throws IOException {
        // Cargar la vista de solicitud de turno
        loadScene("/views/AppointmentView.fxml", 400, 300);
    }

    @FXML
    private void handleLogout() throws IOException {
        // Mostrar alerta de cierre de sesión
        showAlert("Cerrar Sesión", "Has cerrado sesión.", Alert.AlertType.INFORMATION);

        // Cargar la vista de inicio de sesión
        loadScene("/views/LoginView.fxml", 300, 200);
    }

    // Método para cargar una nueva pantalla
    private void loadScene(String fxmlPath, int width, int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent view = loader.load();

        // Mostrar la nueva pantalla
        Stage stage = (Stage) doctorTable.getScene().getWindow();
        stage.setScene(new Scene(view, width, height));
        stage.show();
    }

    // Método para mostrar alertas
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


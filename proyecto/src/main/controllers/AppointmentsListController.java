package controllers;

import database.AppointmentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import models.Appointment;

import java.util.List;

public class AppointmentsListController {

    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, String> doctorColumn;
    @FXML
    private TableColumn<Appointment, String> datetimeColumn;
    @FXML
    private TableColumn<Appointment, Void> actionsColumn;  

    private AppointmentDAO appointmentDAO;

    public AppointmentsListController() {
        // Instancia de DAO para operaciones de la base de datos
        this.appointmentDAO = new AppointmentDAO();
    }

    @FXML
    public void initialize() {
        // Inicializar las columnas de la tabla
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<>("datetime"));

        // Inicializar la columna de acciones para mostrar botones en cada fila
        actionsColumn.setCellFactory(param -> new TableCell<Appointment, Void>() {
            private final Button cancelButton = new Button("Cancelar");

            {
                cancelButton.setOnAction(event -> {
                    Appointment appointment = getTableRow().getItem();
                    if (appointment != null) {
                        handleCancelAppointment(appointment);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(cancelButton);
                }
            }
        });

        // Cargar los turnos activos
        loadActiveAppointments();
    }

    private void loadActiveAppointments() {
        // Obtener todos los turnos activos desde la base de datos
        List<Appointment> activeAppointments = appointmentDAO.getActiveAppointments();

        // Cargar los turnos en la tabla
        appointmentsTable.getItems().setAll(activeAppointments);
    }

    private void handleCancelAppointment(Appointment selectedAppointment) {
        if (selectedAppointment != null) {
            // Llamar al método de cancelación de turno
            boolean success = appointmentDAO.cancelAppointment(selectedAppointment);

            if (success) {
                // Mostrar mensaje de confirmación
                showAlert(Alert.AlertType.INFORMATION, "Cancelación Exitosa", 
                          "El turno con " + selectedAppointment.getDoctor() + " ha sido cancelado.");
                // Recargar la lista de turnos activos
                loadActiveAppointments();
            } else {
                // Si hubo un error
                showAlert(Alert.AlertType.ERROR, "Error", "Hubo un problema al cancelar el turno. Intente nuevamente.");
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

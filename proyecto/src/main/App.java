import database.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Sistema de Turnos Médicos");

        // Inicializa la base de datos y crea las tablas si es necesario
        try {
            DatabaseConnector.initializeDatabase();
        } catch (Exception e) {
            showErrorAlert("Error al iniciar la base de datos", "No se pudo conectar a la base de datos.");
            e.printStackTrace();
            return;
        }

        // Muestra la vista de login
        mostrarVistaLogin();
    }

    // Método para mostrar la vista de login
    public void mostrarVistaLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            showErrorAlert("Error al cargar la vista", "No se pudo cargar la vista de login.");
            e.printStackTrace();
        }
    }

    // Método para mostrar una alerta de error
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

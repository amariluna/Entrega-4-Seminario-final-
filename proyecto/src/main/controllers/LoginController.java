package controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
    
        // Verificación de campos vacíos
        if (username.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese usuario y contraseña.", Alert.AlertType.ERROR);
            return;
        }
    
        // Validación de usuario (aquí se puede mejorar con una base de datos o archivo de configuración)
        if (validarCredenciales(username, password)) {
            mostrarAlerta("Éxito", "Login exitoso!", Alert.AlertType.INFORMATION);
            
            try {
                cargarVistaPrincipal();
            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo cargar la vista principal.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }

    private boolean validarCredenciales(String username, String password) {
        // Validación básica, idealmente aquí deberías comprobar contra una base de datos
        return "admin".equals(username) && "1234".equals(password);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarVistaPrincipal() throws IOException {
        // Cargar la vista principal 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        Parent mainView = loader.load();

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(mainView, 600, 400));  
        stage.show();
    }
}

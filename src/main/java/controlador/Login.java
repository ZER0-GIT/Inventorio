package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label lblContrasena;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblInventorio;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblUsuario;

    @FXML
    private Pane panelSesion;

    @FXML
    private PasswordField passContrasena;

    @FXML
    private TextField txtUsuario;

    @FXML
    void IniciarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/panel/Principal.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Panel Principal");
        stage.setScene(new Scene(root));
        stage.setResizable(false); // opcional
        stage.show();
    }


}


package controlador;

import DAO.UsuarioDAO;
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
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

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
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Label lblError;

    @FXML
    void IniciarSesion(ActionEvent event) throws IOException {
        String usuarioIngresado = txtUsuario.getText();
        String contrasenaIngresada = txtPassword.getText();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.buscarPorNombreUsuario(usuarioIngresado);

        if (user == null) {
            lblError.setText("Usuario no encontrado");
            return;
        }

        boolean valido = BCrypt.checkpw(contrasenaIngresada, user.getContrasenaHash());

        if (!valido) {
            lblError.setText("Contraseña incorrecta");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Principal.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Panel Principal");
        stage.setScene(new Scene(root));
        stage.setResizable(false); // opcional
        stage.show();
    }


}


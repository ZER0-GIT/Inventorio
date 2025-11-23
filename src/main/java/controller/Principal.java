package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Principal implements Initializable {


    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnReporte;

    @FXML
    private StackPane contenidoPrincipal;

    @FXML
    private Label lblTituloMenu;

    @FXML
    void handleInicio(ActionEvent event) {
        lblTituloMenu.setText("Inicio");
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
            contenidoPrincipal.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleProductos(ActionEvent event) {
        lblTituloMenu.setText("Gestión de Productos");
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/view/Producto.fxml"));
            contenidoPrincipal.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleReporte(ActionEvent event) {
        lblTituloMenu.setText("Reportes");
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/view/Reporte.fxml"));
            contenidoPrincipal.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTituloMenu.setText("Inicio");
        try {
            Parent inicio = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
            contenidoPrincipal.getChildren().setAll(inicio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


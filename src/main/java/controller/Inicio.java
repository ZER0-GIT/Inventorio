package controller;

import DAO.ProductoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Inicio {

    @FXML
    private Label lblTotalProductos;

    @FXML
    private Label lblValorInventario;

    @FXML
    private VBox vboxAlertas;

    @FXML
    public void initialize() throws Exception {
        ProductoDAO dao = new ProductoDAO();
        lblTotalProductos.setText(String.valueOf(dao.totalProductos()));
        lblValorInventario.setText(String.valueOf(dao.valorInventario()));
    }

}

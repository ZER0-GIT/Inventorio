package controller;

import DAO.ProductoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

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
        cargarProductosBajoStock();
    }

    public void cargarProductosBajoStock() throws Exception {
        vboxAlertas.getChildren().clear();

        ProductoDAO dao = new ProductoDAO();
        List<model.Producto> lista = dao.listarMenorStock(5);

        for (model.Producto p : lista) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CardProducto.fxml"));
                Parent itemNode = loader.load();

                CardProducto controller = loader.getController();
                controller.setData(p);

                vboxAlertas.getChildren().add(itemNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

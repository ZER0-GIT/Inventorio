package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardProducto {

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblNombreProducto;

    @FXML
    private Label lblProductoID;

    @FXML
    private Label lblStock;

    public void setData(model.Producto p) {
        lblNombreProducto.setText(p.getNombre());
        lblStock.setText(String.valueOf(p.getStock()));
        lblProductoID.setText(String.valueOf(p.getId()));

        int stock = p.getStock();
        String estado;

        if (stock == 0) estado = "Sin stock";
        else if (stock <= 5) estado = "Poco stock";
        else estado = "Disponible";
        lblEstado.setText(estado);
    }

}

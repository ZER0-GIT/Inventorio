package controller;

import DAO.ProductoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;

public class EditProductoForm {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtAlmacen;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtPrecioVenta;

    @FXML
    private TextField txtStock;

    private Producto productoSeleccionado;

    public void setProducto(Producto producto) {
        this.productoSeleccionado = producto;
        txtNombre.setText(producto.getNombre());
        txtDescripcion.setText(producto.getDescripcion());
        txtAlmacen.setText(String.valueOf(producto.getAlmacen()));
        txtStock.setText(String.valueOf(producto.getStock()));
        txtCategoria.setText(String.valueOf(producto.getCategoriaId()));
        txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
        txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
    }

    @FXML
    void guardar(ActionEvent event) throws Exception {

        productoSeleccionado.setNombre(txtNombre.getText());
        productoSeleccionado.setDescripcion(txtDescripcion.getText());
        productoSeleccionado.setAlmacen(Integer.parseInt(txtAlmacen.getText()));
        productoSeleccionado.setStock(Integer.parseInt(txtStock.getText()));
        productoSeleccionado.setCategoriaId(Integer.parseInt(txtCategoria.getText()));
        productoSeleccionado.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText()));
        productoSeleccionado.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText()));

        ProductoDAO dao = new ProductoDAO();
        dao.actualizar(productoSeleccionado);

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }


    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

}

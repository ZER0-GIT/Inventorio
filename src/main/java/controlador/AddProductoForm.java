package controlador;

import DAO.ProductoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;

public class AddProductoForm {

    @FXML
    private Button btnAnadir;

    @FXML
    private Button btnCancel;

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

    @FXML
    void anadir(ActionEvent event) throws Exception {
        Producto producto = new Producto();
        producto.setNombre(txtNombre.getText());
        producto.setDescripcion(txtDescripcion.getText());
        producto.setAlmacen(Integer.parseInt(txtAlmacen.getText()));
        producto.setStock(Integer.parseInt(txtStock.getText()));
        producto.setCategoriaId(Integer.parseInt(txtCategoria.getText()));
        producto.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText()));
        producto.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText()));

        ProductoDAO dao = new ProductoDAO();
        dao.registrar(producto);

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

}


package controlador;

import DAO.ProductoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class Producto {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnFiltrar;

    @FXML
    private TableColumn<model.Producto, Void> ColAcciones;

    @FXML
    private TableColumn<model.Producto, String> ColEstado;

    @FXML
    private TableColumn<model.Producto, Integer> colCantidad;

    @FXML
    private TableColumn<model.Producto, String> colNombre;

    @FXML
    private TableColumn<model.Producto, Double> colPrecio;

    @FXML
    private TableColumn<model.Producto, Integer> colSKU;

    @FXML
    private TableView<model.Producto> tablaProductos;

    @FXML
    public void initialize() throws Exception{
        configurarColumnas();
        ActualizarTabla();
    }

    @FXML
    void agregarProducto(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AddProductoForm.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Agregar producto");
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();

        ActualizarTabla();
    }

    public void ActualizarTabla() throws Exception {
        ProductoDAO dao = new ProductoDAO();
        List<model.Producto> lista = dao.listar();
        tablaProductos.setItems(FXCollections.observableArrayList(lista));
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colSKU.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));

        // Estado según stock
        ColEstado.setCellValueFactory(cellData -> {
            int stock = cellData.getValue().getStock();
            String estado;

            if (stock == 0) estado = "Sin stock";
            else if (stock <= 5) estado = "Poco stock";
            else estado = "Disponible";

            return new SimpleStringProperty(estado);
        });

        agregarBotonesAcciones();
    }

    private void agregarBotonesAcciones() {
        ColAcciones.setCellFactory(col -> new TableCell<>() {

            private final Button btnEditar = new Button();
            private final Button btnEliminar = new Button();

            {
                btnEditar.setGraphic(iconEditar());
                btnEliminar.setGraphic(iconEliminar());

                btnEditar.setStyle("-fx-background-color: transparent;");
                btnEliminar.setStyle("-fx-background-color: transparent;");
                btnEditar.setOnAction(event -> {
                    model.Producto p = getTableView().getItems().get(getIndex());
                    editarProducto(p);
                });

                btnEliminar.setOnAction(event -> {
                    model.Producto p = getTableView().getItems().get(getIndex());
                    try {
                        eliminarProducto(p);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(10, btnEditar, btnEliminar);
                    box.setStyle("-fx-alignment: center;");
                    setGraphic(box);
                }
            }
        });
    }

    private void editarProducto(model.Producto producto){
        System.out.println("Producto modificado");
    }

    private void eliminarProducto(model.Producto producto) throws Exception {
        ProductoDAO pDAO = new ProductoDAO();
        pDAO.eliminar(producto.getId());
        ActualizarTabla();
    }


    //Iconos en SVG
    private SVGPath iconEditar() {
        SVGPath svg = new SVGPath();

        svg.setContent("M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z");
        svg.setStyle("-fx-fill: #3498db; -fx-scale-x: 0.7; -fx-scale-y: 0.7;");
        return svg;
    }

    private SVGPath iconEliminar() {
        SVGPath svg = new SVGPath();
        svg.setContent("M8.586 2.586A2 2 0 0 1 10 2h4a2 2 0 0 1 2 2v2h3a1 1 0 1 1 0 2v12a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V8a1 1 0 0 1 0-2h3V4a2 2 0 0 1 .586-1.414ZM10 6h4V4h-4v2Zm1 4a1 1 0 1 0-2 0v8a1 1 0 1 0 2 0v-8Zm4 0a1 1 0 1 0-2 0v8a1 1 0 1 0 2 0v-8Z");
        svg.setStyle("-fx-fill: #e74c3c; -fx-scale-x: 0.7; -fx-scale-y: 0.7;");
        return svg;
    }




}
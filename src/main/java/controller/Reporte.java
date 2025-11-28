package controller;

import DAO.ProductoDAO;
import model.Sesion;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Reporte {
    @FXML
    private Button btnGenerarReporte;

    @FXML
    void generarReporte(ActionEvent event) throws Exception {
        reporteInventarioPDF();
    }

    private void reporteInventarioPDF() throws Exception {

        String jasperFilePath = "/JasperReports/InventarioReport.jasper";

        ProductoDAO dao = new ProductoDAO();
        List<model.Producto> productos = dao.listar();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        Map<String, Object> params = new HashMap<>();
        params.put("userNombre", Sesion.getInstancia().getUsuario().getNombres());
        params.put("fecha", formatter.format(localDateTime));
        params.put("TABLE_DATA_SOURCE", dataSource);

        InputStream reporte = getClass().getResourceAsStream(jasperFilePath);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(reporte, params, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfFile(jasperPrint, "inventario.pdf");
    }

}

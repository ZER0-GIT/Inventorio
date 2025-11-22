package DAO;

import database.ConexionDB;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductoDAO {
    public boolean registrar(Producto producto) throws Exception{
        String sql = "INSERT INTO productos (nombre, descripcion, categoria_id, almacen, stock, precio_compra, precio_venta)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getCategoriaId());
            stmt.setInt(4, producto.getAlmacen());
            stmt.setInt(5, producto.getStock());
            stmt.setDouble(6, producto.getPrecioCompra());
            stmt.setDouble(7, producto.getPrecioVenta());

            return stmt.executeUpdate() > 0;
        }
    }

}

package DAO;

import database.ConexionDB;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Producto producto) throws Exception {
        String sql = """
        UPDATE productos SET
        nombre = ?, descripcion = ?, categoria_id = ?, almacen = ?, stock = ?, 
        precio_compra = ?, precio_venta = ?
        WHERE id = ?
    """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getCategoriaId());
            stmt.setInt(4, producto.getAlmacen());
            stmt.setInt(5, producto.getStock());
            stmt.setDouble(6, producto.getPrecioCompra());
            stmt.setDouble(7, producto.getPrecioVenta());
            stmt.setInt(8, producto.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    public List<Producto> listar() throws Exception {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setAlmacen(rs.getInt("almacen"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecioCompra(rs.getDouble("precio_compra"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));

                lista.add(producto);
            }
        }
        return lista;
    }

    public Producto buscarID(int id) throws Exception {
        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setAlmacen(rs.getInt("almacen"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecioCompra(rs.getDouble("precio_compra"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                return producto;
            }
        }
        return null;
    }
    public List<Producto> listarStockBajo() throws Exception {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE stock < 5";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("categoria_id"),
                        rs.getInt("almacen"),
                        rs.getInt("stock"),
                        rs.getDouble("precio_compra"),
                        rs.getDouble("precio_venta")
                ));
            }
        }
        return productos;
    }
}

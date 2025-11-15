package DAO;

import model.Usuario;
import database.ConexionDB;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UsuarioDAO {
    public Usuario buscarPorNombreUsuario(String username) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNombreUsuario(rs.getString("nombre_usuario"));
                user.setContrasenaHash(rs.getString("contrasena"));
                user.setCorreo(rs.getString("correo"));
                user.setDNI(rs.getString("dni"));
                user.setTipoUsuario(rs.getString("tipo_usuario"));
                user.setNombres(rs.getString("nombres"));
                user.setTelefono(rs.getString("telefono"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Error DAO: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public boolean crearUsuario(Usuario user) throws Exception {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, nombres, telefono, correo, dni, tipo_usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNombreUsuario());
            String hash = BCrypt.hashpw(user.getContrasenaHash(), BCrypt.gensalt());
            stmt.setString(2, hash);
            stmt.setString(3, user.getNombres());
            stmt.setString(4, user.getTelefono());
            stmt.setString(5, user.getCorreo());
            stmt.setString(6, user.getDNI());
            stmt.setString(7, user.getTipoUsuario());

            return stmt.executeUpdate() > 0;
        }
    }
}

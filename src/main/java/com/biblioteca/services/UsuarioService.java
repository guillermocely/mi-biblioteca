package com.biblioteca.services;

import com.biblioteca.models.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class UsuarioService {
    
    @Resource(lookup = "jdbc/BibliotecaDS")
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    // Autenticación
    public Usuario autenticarUsuario(String username, String password) {
        String sql = "SELECT idUsuario, nombreCompleto, usuario, clave, correo, celular FROM usuarios WHERE usuario = ? AND clave = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("nombreCompleto"),
                    rs.getString("usuario"),
                    rs.getString("clave"),
                    rs.getString("correo"),
                    rs.getString("celular")
                );
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error en autenticación", ex);
        }
        return null;
    }

    // Verificar existencia
    public boolean existeUsuario(String username) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al verificar usuario", ex);
        }
        return false;
    }

    // Registro
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombreCompleto, usuario, clave, correo, celular) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getClave());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getCelular());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error en registro", ex);
        }
        return false;
    }
}
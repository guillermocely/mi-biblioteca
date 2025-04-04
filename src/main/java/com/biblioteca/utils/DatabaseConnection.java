package com.biblioteca.utils;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class DatabaseConnection {
    
    @Resource(lookup = "jdbc/BibliotecaDS")
    private DataSource dataSource;

    // Método de instancia
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    // Versión estática opcional (Singleton)
    private static DatabaseConnection instance;
    
    public static synchronized Connection getStaticConnection() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance.getConnection();
    }
}
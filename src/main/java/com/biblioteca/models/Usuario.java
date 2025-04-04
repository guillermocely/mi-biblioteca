package com.biblioteca.models;

import java.sql.Timestamp;

public class Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private String usuario;
    private String clave;
    private String correo;
    private String celular;
    private Timestamp fechaRegistro;

    // Constructores
    public Usuario() {}

    public Usuario(String nombreCompleto, String usuario, String clave, String correo, String celular) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.clave = clave;
        this.correo = correo;
        this.celular = celular;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
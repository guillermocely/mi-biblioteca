package com.tuproyecto.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")  // Nombre de la tabla en la base de datos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", unique = true, nullable = false)
    private String usuario;

    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Constructor vacío (obligatorio para JPA)
    public Usuario() {
    }

    // Constructor con parámetros (opcional)
    public Usuario(String usuario, String correo, String clave) {
        this.usuario = usuario;
        this.correo = correo;
        this.clave = clave;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters (obligatorios para JPA)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
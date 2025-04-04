package com.biblioteca.controllers;

import com.biblioteca.models.Usuario;
import com.biblioteca.services.UsuarioService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AuthController", urlPatterns = {"/login", "/register", "/logout"})
public class AuthController extends HttpServlet {

    @Inject
    private UsuarioService usuarioService;
    
    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        logger.log(Level.INFO, "Procesando petición POST a: {0}", path);
        
        try {
            if ("/login".equals(path)) {
                procesarLogin(request, response);
            } else if ("/register".equals(path)) {
                procesarRegistro(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en AuthController", e);
            request.setAttribute("error", "Ocurrió un error inesperado");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void procesarLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Usuario y contraseña son requeridos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        Usuario usuario = usuarioService.autenticarUsuario(username, password);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect(request.getContextPath() + "/inicio.jsp");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private void procesarRegistro(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtener parámetros
        String nombreCompleto = request.getParameter("nombreCompleto");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String confirmarClave = request.getParameter("confirmarClave");
        String correo = request.getParameter("correo");
        String celular = request.getParameter("celular");
        
        // Validaciones
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty() ||
            usuario == null || usuario.trim().isEmpty() ||
            clave == null || clave.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty()) {
            
            request.setAttribute("error", "Todos los campos obligatorios deben ser completados");
            request.setAttribute("nombreCompleto", nombreCompleto);
            request.setAttribute("usuario", usuario);
            request.setAttribute("correo", correo);
            request.setAttribute("celular", celular);
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
            return;
        }
        
        if (!clave.equals(confirmarClave)) {
            request.setAttribute("error", "Las contraseñas no coinciden");
            request.setAttribute("nombreCompleto", nombreCompleto);
            request.setAttribute("usuario", usuario);
            request.setAttribute("correo", correo);
            request.setAttribute("celular", celular);
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
            return;
        }
        
        if (usuarioService.existeUsuario(usuario)) {
            request.setAttribute("error", "El nombre de usuario ya está en uso");
            request.setAttribute("nombreCompleto", nombreCompleto);
            request.setAttribute("correo", correo);
            request.setAttribute("celular", celular);
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
            return;
        }
        
        // Crear y registrar usuario
        Usuario nuevoUsuario = new Usuario(nombreCompleto, usuario, clave, correo, celular);
        boolean registrado = usuarioService.registrarUsuario(nuevoUsuario);
        
        if (registrado) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?registro=exitoso");
        } else {
            request.setAttribute("error", "Error al registrar el usuario. Por favor intente nuevamente.");
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if ("/logout".equals(request.getServletPath())) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
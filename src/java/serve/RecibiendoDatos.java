
package serve;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RecibirDatos")
public class RecibiendoDatos extends HttpServlet {

    // Datos de conexión a la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3307/BibliotecaDB";
    private static final String DB_USER = "root"; // Cambia por tu usuario de MySQL
    private static final String DB_PASSWORD = ""; // Cambia por tu contraseña de MySQL

    // Método para procesar la solicitud y generar la respuesta
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String msg)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RecibiendoDatos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado: " + msg + "</h1>");
            out.println("<a href='index.html'>Volver al inicio</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // Método para manejar solicitudes POST (inicio de sesión)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("usuario");
        String pass = request.getParameter("clave");

        try {
            // Conectar a la base de datos
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Consulta para verificar las credenciales
            String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND clave = SHA2(?, 256)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, pass);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Credenciales válidas
                processRequest(request, response, "Ingreso satisfactorio. ¡Bienvenido a la Biblioteca!");
            } else {
                // Credenciales inválidas
                processRequest(request, response, "Ingreso inválido. Usuario o clave incorrectos.");
            }

            // Cerrar recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            processRequest(request, response, "Error en el servidor. Inténtalo de nuevo más tarde.");
        }
    }

    // Método para obtener información del servlet
    @Override
    public String getServletInfo() {
        return "Servlet para manejar el ingreso de usuarios en el sistema de gestión de biblioteca.";
    }
}
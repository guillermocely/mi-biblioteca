<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registro - Sistema Biblioteca</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="login-container">
        <h1>Registro de Usuario</h1>
        
        <!-- Mensajes del servidor -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty passwordError}">
            <div class="alert alert-danger">${passwordError}</div>
        </c:if>
        <c:if test="${not empty exito}">
            <div class="alert alert-success">${exito}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/register" method="POST">
            <!-- Campos del formulario -->
            <div class="form-group">
                <label for="nombreCompleto">Nombre Completo*:</label>
                <input type="text" id="nombreCompleto" name="nombreCompleto" 
                       value="${param.nombreCompleto}" required class="form-control">
            </div>
            
            <div class="form-group">
                <label for="usuario">Nombre de Usuario:</label>
                <input type="text" id="usuario" name="usuario" 
                       value="${param.usuario}" required class="form-control">
            </div>
            
            <div class="form-group">
                <label for="correo">Correo Electrónico:</label>
                <input type="email" id="correo" name="correo" 
                       value="${param.correo}" required class="form-control">
            </div>
            
            <div class="form-group">
                <label for="celular">Teléfono:</label>
                <input type="tel" id="celular" name="celular" 
                       value="${param.celular}" class="form-control">
            </div>
            
            <div class="form-group">
                <label for="clave">Contraseña:</label>
                <input type="password" id="clave" name="clave" required class="form-control">
            </div>
            
            <div class="form-group">
                <label for="confirmarClave">Confirmar Contraseña:</label>
                <input type="password" id="confirmarClave" name="confirmarClave" required class="form-control">
            </div>
            
            <button type="submit" class="btn btn-primary">Registrarse</button>
        </form>
        
        <p class="register-link">¿Ya tienes cuenta? 
            <a href="${pageContext.request.contextPath}/login.jsp">Inicia sesión</a>
        </p>
    </div>
</body>
</html>
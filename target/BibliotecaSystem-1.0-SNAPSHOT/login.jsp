<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login - Sistema Biblioteca</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="login-container">
        <h1>Iniciar Sesión</h1>
        <img src="${pageContext.request.contextPath}/imagenes/logo1.png" width="136" height="198" alt="Logo Biblioteca">

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/login" method="POST">
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required class="form-control">
            </div>
            
            <div class="form-group">
                <label for="clave">Contraseña:</label>
                <input type="password" id="clave" name="clave" required class="form-control">
            </div>
            
            <button type="submit" class="btn btn-primary">Ingresar</button>
        </form>
        
        <p class="register-link">¿No tienes cuenta? <a href="${pageContext.request.contextPath}/registro.jsp">Regístrate aquí</a></p>
    </div>
</body>
</html>
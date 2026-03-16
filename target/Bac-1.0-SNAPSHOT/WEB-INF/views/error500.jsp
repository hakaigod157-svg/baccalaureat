<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erreur Interne (500)</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
    <style>
        .error-container {
            text-align: center;
            padding: 50px 20px;
        }
        .error-code {
            font-size: 6rem;
            color: var(--danger-color);
            margin-bottom: 20px;
        }
        .error-details {
            text-align: left;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            overflow-x: auto;
            margin-top: 30px;
            border: 1px solid #ddd;
            color: #d32f2f;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container error-container">
        <h1 class="error-code">500</h1>
        <h2>Erreur Interne du Serveur</h2>
        <p>Une erreur inattendue s'est produite. Détails ci-dessous :</p>
        
        <div class="error-details">
            <h3>Message d'erreur :</h3>
            <p>${requestScope['javax.servlet.error.message']}</p>
            <p>${requestScope['javax.servlet.error.exception']}</p>
            
            <h3>Trace :</h3>
            <pre>
<c:forEach var="trace" items="${requestScope['javax.servlet.error.exception'].stackTrace}">
    ${trace}
</c:forEach>
            </pre>
        </div>

        <a href="${pageContext.request.contextPath}/" class="btn btn-primary" style="margin-top: 20px;">Retour à l'accueil</a>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page Non Trouvée (404)</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
    <style>
        .error-container {
            text-align: center;
            padding: 100px 20px;
        }
        .error-code {
            font-size: 6rem;
            color: var(--primary-color);
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container error-container">
        <h1 class="error-code">404</h1>
        <h2>Oups ! Page introuvable.</h2>
        <p>La page que vous recherchez n'existe pas ou a été déplacée.</p>
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary" style="margin-top: 20px;">Retour à l'accueil</a>
    </div>
</body>
</html>

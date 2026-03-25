<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${titre} - Forage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbarForage.jsp" />
    <div class="container">
        <h1>${titre}</h1>
        <div class="form-container">
            <form:form action="${pageContext.request.contextPath}/client/sauvegarder" method="post" modelAttribute="client">
                <form:hidden path="idClient" />
                
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <form:input path="nom" id="nom" required="true" class="form-control" />
                </div>
                
                <div class="form-group">
                    <label for="prenom">Prénom :</label>
                    <form:input path="prenom" id="prenom" required="true" class="form-control" />
                </div>

                <div class="form-group">
                    <label for="contact">Contact :</label>
                    +<form:input path="contact" id="contact" type="number" class="form-control" />
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/client/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titre} - Baccalauréat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container" style="max-width: 600px; margin: 0 auto; padding-top: 50px;">
        <div class="form-card">
            <h2 class="form-title">${titre}</h2>
            <form action="${pageContext.request.contextPath}/prof/sauvegarder" method="POST">
                
                <c:if test="${not empty prof.idProf}">
                    <input type="hidden" name="idProf" value="${prof.idProf}">
                </c:if>

                <div class="form-group">
                    <label class="form-label" for="nom">Nom :</label>
                    <input type="text" id="nom" name="nom" class="form-input" value="${prof.nom}" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="prenom">Prénom :</label>
                    <input type="text" id="prenom" name="prenom" class="form-input" value="${prof.prenom}" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="matricule">Matricule :</label>
                    <input type="text" id="matricule" name="matricule" class="form-input" value="${prof.matricule}">
                </div>

                <div class="form-actions" style="margin-top: 30px;">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/prof/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titre} — Baccalauréat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container" style="max-width:640px">
        <div class="form-card reveal">
            <h2 class="form-title">${titre}</h2>
            <form action="${pageContext.request.contextPath}/matiere/sauvegarder" method="POST">

                <c:if test="${not empty matiere.idMatiere}">
                    <input type="hidden" name="idMatiere" value="${matiere.idMatiere}">
                </c:if>

                <div class="form-group">
                    <label class="form-label" for="nom">Nom de la Matière</label>
                    <input type="text" id="nom" name="nom" class="form-input"
                           value="${matiere.nom}" required placeholder="ex: Mathématiques">
                </div>

                <div class="form-group">
                    <label class="form-label" for="serie">Série associée</label>
                    <%-- name="serie" → InitBinder convertit l'ID en objet Serie --%>
                    <select id="serie" name="serie" class="form-input" required>
                        <option value="">-- Sélectionnez une série --</option>
                        <c:forEach var="s" items="${seriesList}">
                            <option value="${s.idSerie}" ${matiere.serie != null && matiere.serie.idSerie == s.idSerie ? 'selected' : ''}>
                                ${s.libelle}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-label" for="coeff">Coefficient</label>
                    <input type="number" step="0.5" id="coeff" name="coeff"
                           class="form-input" value="${matiere.coeff}" required
                           min="1" max="10" placeholder="ex: 4">
                </div>

                <div class="form-actions" style="margin-top:28px">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/matiere/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

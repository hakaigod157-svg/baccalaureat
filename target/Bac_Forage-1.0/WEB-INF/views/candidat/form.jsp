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
            <form action="${pageContext.request.contextPath}/candidat/sauvegarder" method="POST">

                <c:if test="${not empty candidat.idCandidat}">
                    <input type="hidden" name="idCandidat" value="${candidat.idCandidat}">
                </c:if>

                <div class="form-group">
                    <label class="form-label" for="matricule">Matricule (N° Candidat)</label>
                    <input type="text" id="matricule" name="matricule" class="form-input" value="${candidat.matricule}" required placeholder="ex: BAC-2025-001">
                </div>

                <div class="form-group">
                    <label class="form-label" for="nom">Nom</label>
                    <input type="text" id="nom" name="nom" class="form-input" value="${candidat.nom}" required placeholder="Nom de famille">
                </div>

                <div class="form-group">
                    <label class="form-label" for="prenom">Prénom</label>
                    <input type="text" id="prenom" name="prenom" class="form-input" value="${candidat.prenom}" required placeholder="Prénom(s)">
                </div>

                <div class="form-group">
                    <label class="form-label" for="ecole">École</label>
                    <%-- name="ecole" → InitBinder convertit l'ID en objet Ecole --%>
                    <select id="ecole" name="ecole" class="form-input" required>
                        <option value="">-- Sélectionnez une école --</option>
                        <c:forEach var="e" items="${ecolesList}">
                            <option value="${e.idEcole}" ${candidat.ecole != null && candidat.ecole.idEcole == e.idEcole ? 'selected' : ''}>
                                ${e.nomEcole}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-label" for="serie">Série / Filière</label>
                    <%-- name="serie" → InitBinder convertit l'ID en objet Serie --%>
                    <select id="serie" name="serie" class="form-input" required>
                        <option value="">-- Sélectionnez une série --</option>
                        <c:forEach var="s" items="${seriesList}">
                            <option value="${s.idSerie}" ${candidat.serie != null && candidat.serie.idSerie == s.idSerie ? 'selected' : ''}>
                                ${s.libelle}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-actions" style="margin-top:28px">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/candidat/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

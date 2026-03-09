<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div class="container">
        <c:if test="${not empty candidat}">
            <div class="form-card" style="max-width:100%; padding: 24px; margin-bottom: 24px;">
                <h2>${titre}</h2>
                <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 12px; color: var(--text-muted);">
                    <div><strong>Matricule :</strong> ${candidat.matricule}</div>
                    <div><strong>École :</strong> ${candidat.ecole.nomEcole}</div>
                    <div><strong>Série :</strong> ${candidat.serie.libelle}</div>
                </div>
            </div>
        </c:if>

        <c:choose>
            <c:when test="${not empty notes}">
                <table>
                    <thead>
                        <tr>
                            <th>Matière</th>
                            <th>Professeur correcteur</th>
                            <th>Note /20</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="n" items="${notes}">
                            <tr>
                                <td>${n.matiere.nom}</td>
                                <td>${n.prof.nom} ${n.prof.prenom}</td>
                                <td><strong>${n.valeurNote}</strong></td>
                                <td>${n.dateNote}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h3>Aucune note trouvée pour ce candidat.</h3></div>
            </c:otherwise>
        </c:choose>

        <div style="margin-top: 24px;">
            <a href="${pageContext.request.contextPath}/recherche" class="btn btn-secondary">← Retour à la recherche</a>
        </div>
    </div>
</body>
</html>

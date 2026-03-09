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
    <div class="container">
        <h1>${titre}</h1>

        <c:choose>
            <c:when test="${not empty notes}">
                <table>
                    <thead>
                        <tr>
                            <th>Candidat</th>
                            <th>Matière</th>
                            <th>Note /20</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="n" items="${notes}">
                            <tr>
                                <td>${n.candidat.nom} ${n.candidat.prenom}</td>
                                <td>${n.matiere.nom}</td>
                                <td><strong>${n.valeurNote}</strong></td>
                                <td>${n.dateNote}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h3>Aucune note trouvée pour ce professeur.</h3></div>
            </c:otherwise>
        </c:choose>

        <div style="margin-top: 24px;">
            <a href="${pageContext.request.contextPath}/recherche" class="btn btn-secondary">← Retour</a>
        </div>
    </div>
</body>
</html>

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
        <div class="page-header reveal">
            <h1>${titre}</h1>
        </div>
        <div class="reveal-2">
            <c:choose>
                <c:when test="${not empty notes}">
                    <table>
                        <thead>
                            <tr>
                                <th>Candidat</th>
                                <th>Matricule</th>
                                <th>Matière</th>
                                <th>Professeur</th>
                                <th>Note /20</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="n" items="${notes}">
                                <tr>
                                    <td>${n.candidat.nom} ${n.candidat.prenom}</td>
                                    <td class="text-muted">${n.candidat.matricule}</td>
                                    <td>${n.matiere.nom}</td>
                                    <td>${n.prof.nom} ${n.prof.prenom}</td>
                                    <td><span class="note-finale-value">${n.valeurNote}</span></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="empty-state"><h3>Aucune note pour cette série.</h3></div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="reveal-3" style="margin-top: 32px;">
            <a href="${pageContext.request.contextPath}/recherche" class="btn btn-secondary">← Retour à la recherche</a>
        </div>
    </div>
</body>
</html>

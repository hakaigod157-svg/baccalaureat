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
        <div class="page-header reveal">
            <c:if test="${not empty candidat}">
                <div>
                    <h1>${titre}</h1>
                    <div class="page-subtitle mt-8">
                        Matricule: <strong>${candidat.matricule}</strong> &nbsp;&middot;&nbsp; 
                        École: <strong>${candidat.ecole.nomEcole}</strong> &nbsp;&middot;&nbsp; 
                        Série: <strong>${candidat.serie.libelle}</strong>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty candidat}">
                <h1>${titre}</h1>
            </c:if>
        </div>

        <div class="reveal-2">
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
                                    <td><span class="note-finale-value">${n.valeurNote}</span></td>
                                    <td class="text-muted">${n.dateNote}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="empty-state"><h3>Aucune note trouvée pour ce candidat.</h3></div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="reveal-3" style="margin-top: 32px;">
            <a href="${pageContext.request.contextPath}/recherche" class="btn btn-secondary">← Retour à la recherche</a>
        </div>
    </div>
</body>
</html>

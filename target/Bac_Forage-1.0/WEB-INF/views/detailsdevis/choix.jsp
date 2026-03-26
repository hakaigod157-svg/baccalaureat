<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:choose>
            <c:when test="${not empty devisList}">
                <table>
                    <thead>
                        <tr>
                            <th>ID Devis</th>
                            <th>Client (Demande)</th>
                            <th>Type</th>
                            <th>Statut</th>

                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${devisList}">
                            <tr>
                                <td>${item.idDevis}</td>
                                <td>${item.demande.client.nom} (Demande N°${item.demande.idDemande})</td>
                                <td>${item.typeDevis.libelle}</td>
                                <td>${item.statut.libelle}</td>

                                <td>${item.date}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/detailsdevis/devis/${item.idDevis}" class="btn btn-sm btn-primary">Voir Détails</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h2>Aucun Devis disponible</h2></div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

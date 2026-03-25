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
        <div style="margin-bottom: 20px; text-align: right;">
            <a href="${pageContext.request.contextPath}/demande/nouveau" class="btn btn-primary">+ Ajouter une Demande</a>
        </div>
        <c:choose>
            <c:when test="${not empty demandes}">
                <table>
                    <thead>
                        <tr>
                            <th>N° Demande</th>
                            <th>Date</th>
                            <th>Client</th>
                            <th>Lieu</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${demandes}">
                            <tr>
                                <td>${item.idDemande}</td>
                                <td>${item.dateDemande}</td>
                                <td>${item.client.nom} ${item.client.prenom}</td>
                                <td>${item.lieu.localisation}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/demande/modifier/${item.idDemande}" class="btn btn-sm btn-secondary">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/demande/supprimer/${item.idDemande}" class="btn btn-sm btn-danger" onclick="return confirm('Sûr ?');">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h2>Aucune Demande</h2></div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

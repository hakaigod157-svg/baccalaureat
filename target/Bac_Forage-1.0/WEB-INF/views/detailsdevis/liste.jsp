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
            <a href="${pageContext.request.contextPath}/detailsdevis/nouveau" class="btn btn-primary">+ Ajouter Détail</a>
        </div>
        <c:choose>
            <c:when test="${not empty details}">
                <table>
                    <thead>
                        <tr>
                            <th>N° Détail</th>
                            <th>Devis</th>
                            <th>Lieu</th>
                            <th>Libellé</th>
                            <th>Montant</th>
                            <th>Statut</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${details}">
                            <tr>
                                <td>${item.idDetailsDevis}</td>
                                <td>Devis N°${item.devis.idDevis}</td>
                                <td>${item.lieu.localisation}</td>
                                <td>${item.libelle}</td>
                                <td>${item.montant}</td>
                                <td>${item.statut.libelle}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/detailsdevis/modifier/${item.idDetailsDevis}" class="btn btn-sm btn-secondary">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/detailsdevis/supprimer/${item.idDetailsDevis}" class="btn btn-sm btn-danger" onclick="return confirm('Sûr ?');">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h2>Aucun Détail Devis</h2></div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

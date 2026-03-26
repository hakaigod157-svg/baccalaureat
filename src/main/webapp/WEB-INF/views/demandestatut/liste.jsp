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
            <a href="${pageContext.request.contextPath}/demandestatut/nouveau" class="btn btn-primary">+ Changer Statut Demande</a>
        </div>
        <c:choose>
            <c:when test="${not empty demandeStatuts}">
                <table>
                    <thead>
                        <tr>
                            <th>N° Ligne</th>
                            <th>Demande</th>
                            <th>Devis</th>
                            <th>Statut</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${demandeStatuts}">
                            <tr>
                                <td>${item.idDemandeStatut}</td>
                                <td>Demande N°${item.demande.idDemande}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.devis != null}">Devis N°${item.devis.idDevis}</c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${item.statut.libelle}</td>
                                <td>${item.dateDemandeStatut}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/demandestatut/modifier/${item.idDemandeStatut}" class="btn btn-sm btn-secondary">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/demandestatut/supprimer/${item.idDemandeStatut}" class="btn btn-sm btn-danger" onclick="return confirm('Sûr ?');">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h2>Aucun Historique de Statut Demande</h2></div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

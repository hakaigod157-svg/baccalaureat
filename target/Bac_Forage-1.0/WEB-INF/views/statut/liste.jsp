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
            <a href="${pageContext.request.contextPath}/statut/nouveau" class="btn btn-primary">+ Ajouter un Statut</a>
        </div>
        <c:choose>
            <c:when test="${not empty statuts}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Libellé</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${statuts}">
                            <tr>
                                <td>${item.idStatut}</td>
                                <td>${item.libelle}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/statut/modifier/${item.idStatut}" class="btn btn-sm btn-secondary">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/statut/supprimer/${item.idStatut}" class="btn btn-sm btn-danger" onclick="return confirm('Sûr ?');">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h2>Aucun Statut</h2></div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

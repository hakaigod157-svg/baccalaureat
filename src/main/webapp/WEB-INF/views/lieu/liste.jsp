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
            <a href="${pageContext.request.contextPath}/lieu/nouveau" class="btn btn-primary">+ Ajouter un Lieu</a>
        </div>
        <c:choose>
            <c:when test="${not empty lieus}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Localisation</th>
                            <th>District</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${lieus}">
                            <tr>
                                <td>${item.idLieu}</td>
                                <td>${item.localisation}</td>
                                <td>${item.district}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/lieu/modifier/${item.idLieu}" class="btn btn-sm btn-secondary">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/lieu/supprimer/${item.idLieu}" class="btn btn-sm btn-danger" onclick="return confirm('Sûr ?');">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h2>Aucun Lieu</h2></div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

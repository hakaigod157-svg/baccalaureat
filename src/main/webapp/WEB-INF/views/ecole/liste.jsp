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
                
                <div style="margin-bottom: 20px; text-align: right;">
                    <a href="${pageContext.request.contextPath}/ecole/nouveau" class="btn btn-primary">+ Ajouter une
                        Ecole</a>
                </div>

                <c:choose>
                    <c:when test="${not empty ecoles}">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nom</th>
                                    <th>Adresse</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="ecole" items="${ecoles}">
                                    <tr>
                                        <td>${ecole.idEcole}</td>
                                        <td>${ecole.nomEcole}</td>
                                        <td>${ecole.adresse}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ecole/modifier/${ecole.idEcole}"
                                                class="btn btn-sm btn-secondary">Modifier</a>
                                            <a href="${pageContext.request.contextPath}/ecole/supprimer/${ecole.idEcole}"
                                                class="btn btn-sm btn-danger"
                                                onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette ecole ?');">Supprimer</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <h2>Aucune école enregistrée</h2>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </body>
        </html>

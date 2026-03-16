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
                    <a href="${pageContext.request.contextPath}/prof/nouveau" class="btn btn-primary">+ Ajouter un
                        Professeur</a>
                </div>

                <c:choose>
                    <c:when test="${not empty profs}">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Matricule</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="prof" items="${profs}">
                                    <tr>
                                        <td>${prof.idProf}</td>
                                        <td>${prof.nom}</td>
                                        <td>${prof.prenom}</td>
                                        <td>${prof.matricule}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/prof/modifier/${prof.idProf}"
                                                class="btn btn-sm btn-secondary">Modifier</a>
                                            <a href="${pageContext.request.contextPath}/prof/supprimer/${prof.idProf}"
                                                class="btn btn-sm btn-danger"
                                                onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce professeur ?');">Supprimer</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <h2>Aucun professeur enregistré</h2>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </body>
        </html>

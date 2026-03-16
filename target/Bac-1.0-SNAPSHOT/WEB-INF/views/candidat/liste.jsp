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
                    <a href="${pageContext.request.contextPath}/candidat/nouveau" class="btn btn-primary">+ Ajouter un
                        Candidat</a>
                </div>

                <c:choose>
                    <c:when test="${not empty candidats}">
                        <table>
                            <thead>
                                <tr>
                                    <th>Numéro</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>École</th>
                                    <th>Série</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="c" items="${candidats}">
                                    <tr>
                                        <td>${c.matricule}</td>
                                        <td>${c.nom}</td>
                                        <td>${c.prenom}</td>
                                        <td>${c.ecole.nomEcole}</td>
                                        <td>${c.serie.libelle}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/candidat/modifier/${c.idCandidat}"
                                                class="btn btn-sm btn-secondary">Modifier</a>
                                            <a href="${pageContext.request.contextPath}/candidat/supprimer/${c.idCandidat}"
                                                class="btn btn-sm btn-danger"
                                                onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce candidat ?');">Supprimer</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <h2>Aucun candidat enregistré</h2>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </body>
        </html>

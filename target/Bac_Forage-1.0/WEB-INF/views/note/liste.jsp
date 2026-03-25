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
                    <a href="${pageContext.request.contextPath}/note/nouveau" class="btn btn-primary">+ Ajouter une
                        Note</a>
                </div>

                <c:choose>
                    <c:when test="${not empty notes}">
                        <table>
                            <thead>
                                <tr>
                                    <th>Candidat</th>
                                    <th>Matière</th>
                                    <th>Professeur</th>
                                    <th>Note</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="n" items="${notes}">
                                    <tr>
                                        <td>${n.candidat.nom} ${n.candidat.prenom} (${n.candidat.matricule})</td>
                                        <td>${n.matiere.nom}</td>
                                        <td>${n.prof.nom}</td>
                                        <td>
                                            <strong>${n.valeurNote}</strong>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/note/modifier/${n.idNote}"
                                                class="btn btn-sm btn-secondary">Modifier</a>
                                            <a href="${pageContext.request.contextPath}/note/supprimer/${n.idNote}"
                                                class="btn btn-sm btn-danger"
                                                onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette note ?');">Supprimer</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <h2>Aucune note enregistrée</h2>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </body>
        </html>

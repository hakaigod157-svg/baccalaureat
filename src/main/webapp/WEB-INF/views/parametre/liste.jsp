<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titre} — Baccalauréat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container">
        <div class="page-header reveal">
            <div class="page-header-info">
                <h1>${titre}</h1>
                <p class="page-subtitle">Règles de détermination de la note finale selon l'écart entre correcteurs</p>
            </div>
            <a href="${pageContext.request.contextPath}/parametre/nouveau" class="btn btn-primary">+ Nouveau paramètre</a>
        </div>

        <c:choose>
            <c:when test="${not empty parametres}">
                <table class="reveal-2">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Matière</th>
                            <th>Condition</th>
                            <th>Résolution</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- IMPORTANT: 'p' et non 'param' — 'param' est un objet implicite JSTL --%>
                        <c:forEach var="p" items="${parametres}">
                            <tr>
                                <td style="color:var(--text-muted)">${p.idParametre}</td>
                                <td>${p.matiere.nom}</td>
                                <td>
                                    Si écart
                                    <span class="badge badge-gold">${p.operateur.operation}</span>
                                    <strong>${p.diffNotes}</strong>
                                </td>
                                <td><span class="badge">${p.resolution.operation}</span></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/parametre/modifier/${p.idParametre}"
                                       class="btn btn-sm btn-secondary">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/parametre/supprimer/${p.idParametre}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Supprimer ce paramètre ?');">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state reveal-2">
                    <h3>Aucun paramètre enregistré</h3>
                    <p style="margin-top:12px">
                        <a href="${pageContext.request.contextPath}/parametre/nouveau" class="btn btn-primary">Créer le premier paramètre</a>
                    </p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

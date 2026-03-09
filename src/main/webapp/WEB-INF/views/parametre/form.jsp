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
    <div class="container" style="max-width:640px">
        <div class="form-card reveal">
            <h2 class="form-title">${titre}</h2>
            <p style="color:var(--text-muted);margin-bottom:24px;font-size:0.88rem">
                Définissez une règle : si l'écart entre les notes d'un candidat pour une matière
                satisfait la condition, la note finale est déterminée par la résolution choisie.
            </p>
            <form action="${pageContext.request.contextPath}/parametre/sauvegarder" method="POST">

                <c:if test="${not empty parametre.idParametre}">
                    <input type="hidden" name="idParametre" value="${parametre.idParametre}">
                </c:if>

                <div class="form-group">
                    <label class="form-label" for="matiere">Matière concernée</label>
                    <%-- name="matiere" → InitBinder convertit l'ID en objet Matiere --%>
                    <select id="matiere" name="matiere" class="form-input" required>
                        <option value="">-- Sélectionnez une matière --</option>
                        <c:forEach var="m" items="${matieresList}">
                            <option value="${m.idMatiere}" ${parametre.matiere != null && parametre.matiere.idMatiere == m.idMatiere ? 'selected' : ''}>
                                ${m.nom}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div style="display:grid;grid-template-columns:1fr 1fr;gap:16px">
                    <div class="form-group">
                        <label class="form-label" for="operateur">Opérateur (condition)</label>
                        <%-- name="operateur" → InitBinder convertit l'ID en objet Operateur --%>
                        <select id="operateur" name="operateur" class="form-input" required>
                            <option value="">--</option>
                            <c:forEach var="op" items="${operateursList}">
                                <option value="${op.idOperateur}" ${parametre.operateur != null && parametre.operateur.idOperateur == op.idOperateur ? 'selected' : ''}>
                                    ${op.operation}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="diffNotes">Valeur de l'écart</label>
                        <input type="text" id="diffNotes" name="diffNotes" class="form-input"
                               value="${parametre.diffNotes}" required placeholder="ex: 5">
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label" for="resolution">Résolution (note finale)</label>
                    <%-- name="resolution" → InitBinder convertit l'ID en objet Resolution --%>
                    <select id="resolution" name="resolution" class="form-input" required>
                        <option value="">-- Sélectionnez la résolution --</option>
                        <c:forEach var="res" items="${resolutionsList}">
                            <option value="${res.idResolution}" ${parametre.resolution != null && parametre.resolution.idResolution == res.idResolution ? 'selected' : ''}>
                                ${res.operation}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-actions" style="margin-top:28px">
                    <button type="submit" class="btn btn-primary">Enregistrer la règle</button>
                    <a href="${pageContext.request.contextPath}/parametre/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

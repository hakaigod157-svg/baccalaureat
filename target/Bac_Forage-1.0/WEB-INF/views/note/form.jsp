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
            <form action="${pageContext.request.contextPath}/note/sauvegarder" method="POST">

                <c:if test="${not empty note.idNote}">
                    <input type="hidden" name="idNote" value="${note.idNote}">
                </c:if>

                <div class="form-group">
                    <label class="form-label" for="candidat">Candidat</label>
                    <%-- name="candidat" → InitBinder convertit l'ID en objet Candidat --%>
                    <select id="candidat" name="candidat" class="form-input" required>
                        <option value="">-- Sélectionnez un candidat --</option>
                        <c:forEach var="c" items="${candidatsList}">
                            <option value="${c.idCandidat}" ${note.candidat != null && note.candidat.idCandidat == c.idCandidat ? 'selected' : ''}>
                                ${c.nom} ${c.prenom} — ${c.matricule}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-label" for="matiere">Matière</label>
                    <%-- name="matiere" → InitBinder convertit l'ID en objet Matiere --%>
                    <select id="matiere" name="matiere" class="form-input" required>
                        <option value="">-- Sélectionnez une matière --</option>
                        <c:forEach var="m" items="${matieresList}">
                            <option value="${m.idMatiere}" ${note.matiere != null && note.matiere.idMatiere == m.idMatiere ? 'selected' : ''}>
                                ${m.nom} (Série : ${m.serie.libelle}) — Coeff. ${m.coeff}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-label" for="prof">Professeur correcteur</label>
                    <%-- name="prof" → InitBinder convertit l'ID en objet Prof --%>
                    <select id="prof" name="prof" class="form-input" required>
                        <option value="">-- Sélectionnez un professeur --</option>
                        <c:forEach var="p" items="${profsList}">
                            <option value="${p.idProf}" ${note.prof != null && note.prof.idProf == p.idProf ? 'selected' : ''}>
                                ${p.nom} ${p.prenom}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-label" for="valeurNote">Note obtenue (sur 20)</label>
                    <input type="number" step="0.25" id="valeurNote" name="valeurNote"
                           class="form-input" value="${note.valeurNote}"
                           required min="0" max="20" placeholder="ex: 14.5">
                </div>

                <div class="form-actions" style="margin-top:28px">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/note/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notes Finales — Baccalauréat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
    <style>
        .filter-panel {
            background: var(--bg-card);
            border: 1px solid var(--border);
            border-radius: var(--radius-lg);
            padding: 32px;
            margin-bottom: 40px;
        }
        .filter-panel h2 {
            font-size: 1.2rem;
            color: var(--gold);
            margin-bottom: 20px;
            padding-bottom: 12px;
            border-bottom: 1px solid var(--border);
        }
        .filter-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 16px;
            align-items: end;
        }
        @media (max-width: 700px) { .filter-grid { grid-template-columns: 1fr; } }
        .filter-grid .form-group { margin-bottom: 0; }

        .results-panel {
            animation: fadeUp 0.5s ease both;
        }
        .scroll-wrapper { overflow-x: auto; }
        th, td { white-space: nowrap; }
        .note-ok { color: var(--gold); font-weight: 700; font-family: 'Cormorant Garamond', serif; font-size: 1.05rem; }
        .note-low { color: #e74c3c; font-weight: 700; font-family: 'Cormorant Garamond', serif; font-size: 1.05rem; }
        .note-high { color: #27ae60; font-weight: 700; font-family: 'Cormorant Garamond', serif; font-size: 1.05rem; }
        .ecart-badge {
            display: inline-block;
            font-size: 0.65rem;
            padding: 2px 6px;
            border-radius: 8px;
            background: rgba(201,168,76,0.12);
            border: 1px solid rgba(201,168,76,0.25);
            color: var(--gold-light);
            margin-left: 4px;
        }
        .legend-row {
            display: flex;
            gap: 20px;
            margin-bottom: 16px;
            font-size: 0.8rem;
            color: var(--text-muted);
            flex-wrap: wrap;
        }
        .legend-row span { display: flex; align-items: center; gap: 6px; }
        .legend-dot { width: 10px; height: 10px; border-radius: 50%; display: inline-block; }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container">
        <div class="page-header">
            <div class="page-header-info">
                <h1>Notes Finales</h1>
                <p class="page-subtitle">Résultats calculés selon la logique d'écart définie dans les Paramètres</p>
            </div>
            <div class="flex gap-12">
                <a href="${pageContext.request.contextPath}/recherche" class="btn btn-secondary">Retour</a>
                <a href="${pageContext.request.contextPath}/parametre/liste" class="btn btn-primary">Paramètres d'écart</a>
            </div>
        </div>

        <!-- FORMULAIRE CRITÈRES -->
        <div class="filter-panel reveal">
            <h2>Sélectionner les critères d'affichage</h2>
            <form method="GET" action="${pageContext.request.contextPath}/recherche/notes-finales">
                <div class="filter-grid">
                    <div class="form-group">
                        <label class="form-label">Mot clé (Etudiant)</label>
                        <input type="text" name="mc" value="${mc}" class="form-input" placeholder="Nom, Prénom, Matricule...">
                    </div>
                    <div class="form-group">
                        <label class="form-label">Filtrer par Matière</label>
                        <select name="matiereId" class="form-input">
                            <option value="">Toutes les matières</option>
                            <c:forEach var="m" items="${matieresList}">
                                <option value="${m.idMatiere}" ${matiereId == m.idMatiere ? 'selected' : ''}>${m.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Filtrer par Série</label>
                        <select name="serieId" class="form-input">
                            <option value="">Toutes les séries</option>
                            <c:forEach var="s" items="${series}">
                                <option value="${s.idSerie}" ${serieId == s.idSerie ? 'selected' : ''}>${s.libelle}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Filtrer par Ecole</label>
                        <select name="ecoleId" class="form-input">
                            <option value="">Toutes les écoles</option>
                            <c:forEach var="e" items="${ecoles}">
                                <option value="${e.idEcole}" ${ecoleId == e.idEcole ? 'selected' : ''}>${e.nomEcole}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-label">&nbsp;</label>
                        <button type="submit" class="btn btn-primary w-full">Afficher les résultats</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- RÉSULTATS -->
        <c:if test="${resultsReady}">
            <div class="results-panel">
                <div class="legend-row">
                    <span><span class="legend-dot" style="background:#27ae60"></span> Note finale &ge; 10</span>
                    <span><span class="legend-dot" style="background:#c0392b"></span> Note finale &lt; 10</span>
                    <span><span class="legend-dot" style="background:var(--gold)"></span> Note unique (pas d'écart)</span>
                    <span><span class="ecart-badge">écart</span> Valeur de l'écart entre les notes</span>
                </div>

                <c:choose>
                    <c:when test="${not empty candidats}">
                        <div class="scroll-wrapper">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Candidat</th>
                                        <th>Matricule</th>
                                        <th>Série</th>
                                        <c:forEach var="m" items="${matieres}">
                                            <th>${m.nom}</th>
                                        </c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="c" items="${candidats}">
                                        <tr>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/recherche/candidat/${c.idCandidat}">
                                                    ${c.nom} ${c.prenom}
                                                </a>
                                            </td>
                                            <td style="color:var(--text-muted);font-size:0.82rem">${c.matricule}</td>
                                            <td><span class="badge badge-gold">${c.serie.libelle}</span></td>
                                            <c:forEach var="m" items="${matieres}">
                                                <td>
                                                    <c:set var="nf" value="${notesFinalesMap[c.idCandidat][m.idMatiere]}" />
                                                    <c:set var="ec" value="${ecartsMap[c.idCandidat][m.idMatiere]}" />
                                                    <c:choose>
                                                        <c:when test="${nf != null}">
                                                            <c:choose>
                                                                <c:when test="${nf >= 10}">
                                                                    <span class="note-high"><fmt:formatNumber value="${nf}" maxFractionDigits="2"/></span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="note-low"><fmt:formatNumber value="${nf}" maxFractionDigits="2"/></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <span style="color:var(--text-muted);font-size:0.75rem">/20</span>
                                                            <c:if test="${ec > 0}">
                                                                <span class="ecart-badge">ec. <fmt:formatNumber value="${ec}" maxFractionDigits="1"/></span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span style="color:var(--text-dim)">—</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <h3>Aucun candidat pour ces critères.</h3>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </div>
</body>
</html>

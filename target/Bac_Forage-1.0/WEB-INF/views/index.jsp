<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord — Baccalauréat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container reveal">
        <!-- Hero header -->
        <div class="hero-line">
            <h1>Tableau de Bord</h1>
            <span></span>
        </div>

        <!-- Statistiques globales -->
        <div class="stats-grid">
            <a href="${pageContext.request.contextPath}/candidat/liste" class="stat-card reveal">
                <div class="stat-number">${totalCandidats}</div>
                <div class="stat-label">Candidats</div>
            </a>
            <a href="${pageContext.request.contextPath}/prof/liste" class="stat-card reveal-2">
                <div class="stat-number">${totalProfs}</div>
                <div class="stat-label">Professeurs</div>
            </a>
            <a href="${pageContext.request.contextPath}/matiere/liste" class="stat-card reveal-3">
                <div class="stat-number">${totalMatieres}</div>
                <div class="stat-label">Matières</div>
            </a>
            <a href="${pageContext.request.contextPath}/note/liste" class="stat-card reveal">
                <div class="stat-number">${totalNotes}</div>
                <div class="stat-label">Notes saisies</div>
            </a>
            <a href="${pageContext.request.contextPath}/serie/liste" class="stat-card reveal-2">
                <div class="stat-number">${totalSeries}</div>
                <div class="stat-label">Séries</div>
            </a>
            <a href="${pageContext.request.contextPath}/ecole/liste" class="stat-card reveal-3">
                <div class="stat-number">${totalEcoles}</div>
                <div class="stat-label">Ecoles</div>
            </a>
            <a href="${pageContext.request.contextPath}/parametre/liste" class="stat-card reveal">
                <div class="stat-number">${totalParametres}</div>
                <div class="stat-label">Paramètres</div>
            </a>
        </div>

        <!-- Actions rapides -->
        <div class="actions-row">
            <a href="${pageContext.request.contextPath}/candidat/nouveau" class="action-tile">
                <div class="tile-label">Inscrire</div>
                <div class="tile-title">Nouveau Candidat</div>
            </a>
            <a href="${pageContext.request.contextPath}/note/nouveau" class="action-tile">
                <div class="tile-label">Saisir</div>
                <div class="tile-title">Note de copie</div>
            </a>
            <a href="${pageContext.request.contextPath}/recherche" class="action-tile">
                <div class="tile-label">Consulter</div>
                <div class="tile-title">Recherche avancée</div>
            </a>
            <a href="${pageContext.request.contextPath}/recherche/notes-finales" class="action-tile" style="border-color: rgba(201,168,76,0.3);">
                <div class="tile-label">Résultats</div>
                <div class="tile-title" style="color:var(--gold)">Notes Finales BACC</div>
            </a>
            <a href="${pageContext.request.contextPath}/parametre/liste" class="action-tile">
                <div class="tile-label">Configurer</div>
                <div class="tile-title">Paramètres d'écart</div>
            </a>
        </div>

        <!-- Tableaux résumés -->
        <div class="data-section">
            <div>
                <div class="section-header">
                    <span class="section-title-text">Candidats inscrits</span>
                    <a href="${pageContext.request.contextPath}/candidat/liste" class="btn btn-secondary btn-sm">Voir tout</a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Matricule</th>
                            <th>Nom & Prénom</th>
                            <th>Série</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${derniersCandidats}" varStatus="st">
                            <c:if test="${st.index < 7}">
                                <tr>
                                    <td style="color:var(--text-muted);font-size:0.82rem">${c.matricule}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/recherche/candidat/${c.idCandidat}">
                                            ${c.nom} ${c.prenom}
                                        </a>
                                    </td>
                                    <td>${c.serie.libelle}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <c:if test="${empty derniersCandidats}">
                            <tr><td colspan="3" style="text-align:center;color:var(--text-muted)">Aucun candidat</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            <div>
                <div class="section-header">
                    <span class="section-title-text">Dernières notes</span>
                    <a href="${pageContext.request.contextPath}/note/liste" class="btn btn-secondary btn-sm">Voir tout</a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Candidat</th>
                            <th>Matière</th>
                            <th>Note</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="n" items="${dernieresNotes}" varStatus="st">
                            <c:if test="${st.index < 7}">
                                <tr>
                                    <td>${n.candidat.nom} ${n.candidat.prenom}</td>
                                    <td>${n.matiere.nom}</td>
                                    <td><span class="note-finale-value">${n.valeurNote}</span><span style="color:var(--text-muted);font-size:0.78rem">/20</span></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <c:if test="${empty dernieresNotes}">
                            <tr><td colspan="3" style="text-align:center;color:var(--text-muted)">Aucune note</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>

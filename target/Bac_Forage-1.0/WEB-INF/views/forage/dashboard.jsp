<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord — Forage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbarForage.jsp" />
    <div class="container reveal">
        <!-- Hero header -->
        <div class="hero-line">
            <h1>Tableau de Bord</h1>
            <span></span>
        </div>

        <!-- Statistiques globales -->
        <div class="stats-grid">
            <a href="${pageContext.request.contextPath}/client/liste" class="stat-card reveal">
                <div class="stat-number">${totalClients}</div>
                <div class="stat-label">Clients</div>
            </a>
            <a href="${pageContext.request.contextPath}/demande/liste" class="stat-card reveal-2">
                <div class="stat-number">${totalDemandes}</div>
                <div class="stat-label">Demandes</div>
            </a>
            <a href="${pageContext.request.contextPath}/devis/liste" class="stat-card reveal-3">
                <div class="stat-number">${totalDevis}</div>
                <div class="stat-label">Devis</div>
            </a>
            <a href="${pageContext.request.contextPath}/lieu/liste" class="stat-card reveal">
                <div class="stat-number">${totalLieux}</div>
                <div class="stat-label">Lieux</div>
            </a>
            <a href="${pageContext.request.contextPath}/statut/liste" class="stat-card reveal-2">
                <div class="stat-number">${totalStatuts}</div>
                <div class="stat-label">Statuts</div>
            </a>
            <a href="${pageContext.request.contextPath}/typedevis/liste" class="stat-card reveal-3">
                <div class="stat-number">${totalTypeDevis}</div>
                <div class="stat-label">Types Devis</div>
            </a>
            <a href="${pageContext.request.contextPath}/demandestatut/liste" class="stat-card reveal">
                <div class="stat-number">${totalDemandeStatuts}</div>
                <div class="stat-label">Demande Statuts</div>
            </a>
            <a href="${pageContext.request.contextPath}/detailsdevis/liste" class="stat-card reveal-2">
                <div class="stat-number">${totalDetailsDevis}</div>
                <div class="stat-label">Détails Devis</div>
            </a>
        </div>

        <!-- Actions rapides -->
        <div class="actions-row">
            <a href="${pageContext.request.contextPath}/demande/nouveau" class="action-tile">
                <div class="tile-label">Créer</div>
                <div class="tile-title">Nouvelle Demande</div>
            </a>
            <a href="${pageContext.request.contextPath}/devis/nouveau" class="action-tile">
                <div class="tile-label">Créer</div>
                <div class="tile-title">Nouveau Devis</div>
            </a>
            <a href="${pageContext.request.contextPath}/client/nouveau" class="action-tile">
                <div class="tile-label">Inscrire</div>
                <div class="tile-title">Nouveau Client</div>
            </a>
            <a href="${pageContext.request.contextPath}/statut/liste" class="action-tile" style="border-color: rgba(201,168,76,0.3);">
                <div class="tile-label">Consulter</div>
                <div class="tile-title" style="color:var(--gold)">Statuts</div>
            </a>
        </div>

        <!-- Tableaux résumés -->
        <div class="data-section">
            <div>
                <div class="section-header">
                    <span class="section-title-text">Dernières demandes</span>
                    <a href="${pageContext.request.contextPath}/demande/liste" class="btn btn-secondary btn-sm">Voir tout</a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Client</th>
                            <th>Lieu</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="d" items="${dernieresDemandes}" varStatus="st">
                            <c:if test="${st.index < 7}">
                                <tr>
                                    <td>${d.client.nom} ${d.client.prenom}</td>
                                    <td>${d.lieu.localisation}</td>
                                    <td style="color:var(--text-muted);font-size:0.82rem">${d.dateDemande}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <c:if test="${empty dernieresDemandes}">
                            <tr><td colspan="3" style="text-align:center;color:var(--text-muted)">Aucune demande</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            <div>
                <div class="section-header">
                    <span class="section-title-text">Derniers devis</span>
                    <a href="${pageContext.request.contextPath}/devis/liste" class="btn btn-secondary btn-sm">Voir tout</a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Type</th>
                            <th>Statut</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dv" items="${derniersDevis}" varStatus="st">
                            <c:if test="${st.index < 7}">
                                <tr>
                                    <td>${dv.typeDevis.libelle}</td>
                                    <td>${dv.statut.libelle}</td>
                                    <td style="color:var(--text-muted);font-size:0.82rem">${dv.date}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <c:if test="${empty derniersDevis}">
                            <tr><td colspan="3" style="text-align:center;color:var(--text-muted)">Aucun devis</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titre} - Baccalauréat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
    <style>
        .search-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        .search-card {
            background: var(--card-bg);
            border: 1px solid var(--border-color);
            border-radius: var(--radius-lg);
            padding: 28px;
        }
        .search-card h3 {
            margin-top: 0;
            color: var(--primary-color);
            font-size: 1.05rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        .search-card select, .search-card input {
            width: 100%;
            margin-bottom: 12px;
        }
        .quick-link {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 10px 18px;
            background: rgba(var(--primary-rgb),0.12);
            border: 1px solid var(--primary-color);
            border-radius: var(--radius-md);
            color: var(--primary-color);
            text-decoration: none;
            font-size: 0.9rem;
            margin: 6px 6px 0 0;
            transition: all 0.2s;
        }
        .quick-link:hover {
            background: var(--primary-color);
            color: #000;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container">
        <h1>${titre}</h1>
        <p style="color:var(--text-muted)">Recherchez et consultez les notes par différents critères.</p>

        <div class="search-grid">
            <!-- Recherche par candidat -->
            <div class="search-card">
                <h3>Par Candidat</h3>
                <form action="#" id="formCandidat">
                    <select id="selectCandidat" class="form-input" onchange="gotoUrl('/recherche/candidat/', this.value)">
                        <option value="">-- Sélectionnez un candidat --</option>
                        <c:forEach var="c" items="${candidats}">
                            <option value="${c.idCandidat}">${c.nom} ${c.prenom} (${c.matricule})</option>
                        </c:forEach>
                    </select>
                </form>
            </div>

            <!-- Recherche par professeur -->
            <div class="search-card">
                <h3> Par Professeur</h3>
                <select class="form-input" onchange="gotoUrl('/recherche/prof/', this.value)">
                    <option value="">-- Sélectionnez un professeur --</option>
                    <c:forEach var="p" items="${profs}">
                        <option value="${p.idProf}">${p.nom} ${p.prenom}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Recherche par matière -->
            <div class="search-card">
                <h3>Par Matière</h3>
                <select class="form-input" onchange="gotoUrl('/recherche/matiere/', this.value)">
                    <option value="">-- Sélectionnez une matière --</option>
                    <c:forEach var="m" items="${matieres}">
                        <option value="${m.idMatiere}">${m.nom}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Recherche par série -->
            <div class="search-card">
                <h3>Par Série / Filière</h3>
                <select class="form-input" onchange="gotoUrl('/recherche/serie/', this.value)">
                    <option value="">-- Sélectionnez une série --</option>
                    <c:forEach var="s" items="${series}">
                        <option value="${s.idSerie}">${s.libelle}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div style="margin-top: 40px; text-align: center;">
            <h2 style="color: var(--text-muted); font-size: 1rem; text-transform: uppercase;">Accès direct</h2>
            <a href="${pageContext.request.contextPath}/recherche/notes-finales" class="quick-link"> Consulter les Notes Finales</a>
            <a href="${pageContext.request.contextPath}/note/liste" class="quick-link">Toutes les Notes</a>
            <a href="${pageContext.request.contextPath}/candidat/liste" class="quick-link">Tous les Candidats</a>
        </div>
    </div>

    <script>
        function gotoUrl(base, id) {
            if (id) {
                window.location.href = '${pageContext.request.contextPath}' + base + id;
            }
        }
    </script>
</body>
</html>

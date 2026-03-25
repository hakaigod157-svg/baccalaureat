<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${titre} - Forage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbarForage.jsp" />
    <div class="container">
        <h1>${titre}</h1>
        <div class="form-container">
            <form:form action="${pageContext.request.contextPath}/demandestatut/sauvegarder" method="post" modelAttribute="demandeStatut">
                <form:hidden path="idDemandeStatut" />
                
                <div class="form-group">
                    <label>Demande :</label>
                    <form:select path="demande" class="form-control">
                        <c:forEach items="${demandesList}" var="d">
                            <form:option value="${d.idDemande}">Demande N°${d.idDemande} - Client: ${d.client.nom}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="form-group">
                    <label>Statut :</label>
                    <form:select path="statut" class="form-control">
                        <c:forEach items="${statutsList}" var="s">
                            <form:option value="${s.idStatut}">${s.libelle}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/demandestatut/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>

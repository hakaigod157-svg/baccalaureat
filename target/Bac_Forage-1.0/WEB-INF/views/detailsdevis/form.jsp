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
            <form:form action="${pageContext.request.contextPath}/detailsdevis/sauvegarder" method="post" modelAttribute="detail">
                <form:hidden path="idDetailsDevis" />
                <form:hidden path="devis" value="${idDevis}" />

                <div class="form-group">
                    <label>Libellé :</label>
                    <form:input path="libelle" class="form-control" />
                </div>

                <div class="form-group">
                    <label>Prix Unitaire :</label>
                    <form:input path="prixUnitaire" type="number" step="0.01" class="form-control" />
                </div>

                <div class="form-group">
                    <label>Quantité :</label>
                    <form:input path="quantite" type="number" min="1" class="form-control" />
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
                    <a href="${pageContext.request.contextPath}/detailsdevis/devis/${idDevis}" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>

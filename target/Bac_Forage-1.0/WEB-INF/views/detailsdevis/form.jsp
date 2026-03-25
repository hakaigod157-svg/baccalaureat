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
                
                <div class="form-group">
                    <label>Devis :</label>
                    <form:select path="devis" class="form-control">
                        <c:forEach items="${devisList}" var="d">
                            <form:option value="${d.idDevis}">Devis N°${d.idDevis} - ${d.typeDevis.libelle}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                
                <div class="form-group">
                    <label>Lieu :</label>
                    <form:select path="lieu" class="form-control">
                        <c:forEach items="${lieusList}" var="l">
                            <form:option value="${l.idLieu}">${l.localisation} - ${l.district}</form:option>
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

                <div class="form-group">
                    <label>Libellé (Détail) :</label>
                    <form:input path="libelle" class="form-control" />
                </div>

                <div class="form-group">
                    <label>Montant :</label>
                    <form:input path="montant" type="number" step="0.01" class="form-control" />
                </div>
                

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/detailsdevis/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>

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
            <form:form action="${pageContext.request.contextPath}/demande/sauvegarder" method="post" modelAttribute="demande">
                <form:hidden path="idDemande" />
                
                <div class="form-group">
                    <label for="client">Client :</label>
                    <form:select path="client" id="client" class="form-control">
                        <c:forEach items="${clientsList}" var="c">
                            <form:option value="${c.idClient}">${c.nom} ${c.prenom}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                
                <div class="form-group">
                    <label for="lieu">Lieu :</label>
                    <form:select path="lieu" id="lieu" class="form-control">
                        <c:forEach items="${lieusList}" var="l">
                            <form:option value="${l.idLieu}">${l.localisation} - ${l.district}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="form-group">
                    <label for="dateDemande">Date de demande :</label>
                    <form:input path="dateDemande" id="dateDemande" type="datetime-local" class="form-control" />
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/demande/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>

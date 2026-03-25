<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titre} - Baccalauréat</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbar.jsp" />
    <div class="container" style="max-width: 600px; margin: 0 auto; padding-top: 50px;">
        <div class="form-card">
            <h2 class="form-title">${titre}</h2>
            <form action="${pageContext.request.contextPath}/operateur/sauvegarder" method="POST">
                
                <c:if test="${not empty operateur.idOperateur}">
                    <input type="hidden" name="idOperateur" value="${operateur.idOperateur}">
                </c:if>

                <div class="form-group">
                    <label class="form-label" for="operateur">Symbole :</label>
                    <input type="text" id="operateur" name="operation" class="form-input" value="${operateur.operation}" required
                           placeholder="ex: <, >, =">
                </div>

                <div class="form-actions" style="margin-top: 30px;">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/operateur/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

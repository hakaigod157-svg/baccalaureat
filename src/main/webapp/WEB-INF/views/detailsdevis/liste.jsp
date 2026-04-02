<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div style="margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center;">
            <a href="${pageContext.request.contextPath}/detailsdevis/liste" class="btn btn-secondary">&larr; Retour aux Devis</a>
            <a href="${pageContext.request.contextPath}/detailsdevis/devis/${devis.idDevis}/nouveau" class="btn btn-primary">+ Ajouter Détail</a>
        </div>

        <c:choose>
            <c:when test="${not empty details}">
                <table>
                    <thead>
                        <tr>
                            <th>N° Détail</th>
                            <th>Libellé</th>
                            <th>Prix Unitaire</th>
                            <th>Quantité</th>
                            <!-- <th>Montant</th> -->
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- <c:set var="total" value="0" />
                        <c:forEach var="item" items="${details}">
                            <c:set var="montantLigne" value="${item.prixUnitaire * item.quantite}" /> -->
                            <!-- <c:set var="total" value="${total + montantLigne}" /> -->
                            <tr>
                                <td>${item.idDetailsDevis}</td>
                                <td>${item.libelle}</td>
                                <td>${item.prixUnitaire}</td>
                                <td>${item.quantite}</td>
                                <!-- <td><strong>${montantLigne}</strong></td> -->
                                <td>
                                    <a href="${pageContext.request.contextPath}/detailsdevis/modifier/${item.idDetailsDevis}" class="btn btn-sm btn-secondary">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/detailsdevis/supprimer/${item.idDetailsDevis}" class="btn btn-sm btn-danger" onclick="return confirm('Sûr ?');">Supprimer</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" style="text-align: right; font-weight: 700;">Montant Total :</td>
                            <td><strong>${total}</strong></td>
                            <td colspan="2"></td>
                        </tr>
                    </tfoot>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state"><h2>Aucun détail pour ce devis</h2></div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

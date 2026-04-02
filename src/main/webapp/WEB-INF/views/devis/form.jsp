<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${titre} - Forage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/luxury.css">
    <style>
        .info-demande {
            background: #f8f9fa;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 16px 20px;
            margin-bottom: 20px;
            display: none;
        }
        .info-demande.visible { display: block; }
        .info-demande p { margin: 4px 0; font-size: 0.95em; }
        .info-demande .label { font-weight: 600; color: #333; }

        .detail-section { margin-top: 30px; }
        .detail-section h2 { margin-bottom: 12px; font-size: 1.2em; }

        .detail-table { width: 100%; border-collapse: collapse; margin-bottom: 10px; }
        .detail-table th, .detail-table td { padding: 8px 10px; text-align: left; border-bottom: 1px solid #e0e0e0; }
        .detail-table th { font-weight: 600; font-size: 0.85em; text-transform: uppercase; color: #666; }
        .detail-table input, .detail-table select { width: 100%; box-sizing: border-box; }
        .detail-table .montant-cell { font-weight: 600; min-width: 100px; }

        .total-row { display: flex; justify-content: flex-end; align-items: center; gap: 16px; margin: 16px 0; padding: 12px 20px; background: #f0f0f0; border-radius: 8px; }
        .total-row .label { font-weight: 700; font-size: 1.1em; }
        .total-row .value { font-weight: 700; font-size: 1.2em; color: #111; }

        .btn-add-line { background: #333; color: #fff; border: none; padding: 8px 16px; border-radius: 6px; cursor: pointer; font-size: 0.9em; }
        .btn-add-line:hover { background: #555; }
        .btn-remove { background: #c0392b; color: white; border: none; padding: 4px 10px; border-radius: 4px; cursor: pointer; font-size: 0.8em; }
        .btn-remove:hover { background: #e74c3c; }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/navbarForage.jsp" />
    <div class="container">
        <h1>${titre}</h1>
        <div class="form-container">
            <form:form action="${pageContext.request.contextPath}/devis/sauvegarder" method="post" modelAttribute="devis" id="devisForm">
                <form:hidden path="idDevis" />

                <div class="form-group">
                    <label>Demande :</label>
                    <form:select path="demande" class="form-control" id="selectDemande" onchange="chargerInfoDemande()">
                        <option value="">-- Sélectionner une demande --</option>
                        <c:forEach items="${demandesList}" var="d">
                            <form:option value="${d.idDemande}">Demande N°${d.idDemande} - ${d.client.nom}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                
                <div class="info-demande" id="infoDemande">
                    <p><span class="label">Client :</span> <span id="infoClient">-</span></p>
                    <p><span class="label">Date Demande :</span> <span id="infoDate">-</span></p>
                    <p><span class="label">Lieu :</span> <span id="infoLieu">-</span></p>
                    <p><span class="label">Etat de la demande :</span> <span id="infoEtat">-</span></p>
                </div>

                <div class="form-group">
                    <label>Type Devis :</label>
                    <form:select path="typeDevis" class="form-control">
                        <c:forEach items="${typesList}" var="t">
                            <form:option value="${t.idTypeDevis}">${t.libelle}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="form-group">
                    <label>Date :</label>
                    <form:input path="date" type="datetime-local" class="form-control" />
                </div>

                <!-- Section Details -->
                <div class="detail-section">
                    <h2>Lignes de Détail</h2>
                    <table class="detail-table" id="detailTable">
                        <thead>
                            <tr>
                                <th>Libellé</th>
                                <th>Prix Unitaire</th>
                                <th>Quantité</th>
                                <th>Montant</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="detailBody">
                        </tbody>
                    </table>
                    <button type="button" class="btn-add-line" onclick="ajouterLigne()">+ Ajouter une ligne</button>

                    <div class="total-row">
                        <span class="label">Montant Total :</span>
                        <span class="value" id="montantTotal">0.00</span>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/devis/liste" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>

    <script>
        var contextPath = '${pageContext.request.contextPath}';

        function chargerInfoDemande() {
            var select = document.getElementById('selectDemande');
            var idDemande = select.value;
            var infoDiv = document.getElementById('infoDemande');

            if (!idDemande) {
                infoDiv.classList.remove('visible');
                return;
            }

            var xhr = new XMLHttpRequest();
            xhr.open('GET', contextPath + '/devis/demande/' + idDemande, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var data = JSON.parse(xhr.responseText);
                    document.getElementById('infoClient').textContent = data.clientNom + ' ' + data.clientPrenom;
                    document.getElementById('infoDate').textContent = data.dateDemande || '-';
                    document.getElementById('infoLieu').textContent = data.lieu || '-';
                    document.getElementById('infoEtat').textContent = data.etatDemande || '-';
                    infoDiv.classList.add('visible');
                }
            };
            xhr.send();
        }

        function ajouterLigne() {
            var tbody = document.getElementById('detailBody');
            var row = document.createElement('tr');

            var tdLib = document.createElement('td');
            var inputLib = document.createElement('input');
            inputLib.type = 'text';
            inputLib.name = 'detail_libelle';
            inputLib.className = 'form-control';
            inputLib.required = true;
            tdLib.appendChild(inputLib);
            row.appendChild(tdLib);

            var tdPU = document.createElement('td');
            var inputPU = document.createElement('input');
            inputPU.type = 'number';
            inputPU.step = '0.01';
            inputPU.name = 'detail_prixUnitaire';
            inputPU.className = 'form-control';
            inputPU.required = true;
            inputPU.oninput = function() { calculerMontantLigne(row); };
            tdPU.appendChild(inputPU);
            row.appendChild(tdPU);

            var tdQty = document.createElement('td');
            var inputQty = document.createElement('input');
            inputQty.type = 'number';
            inputQty.min = '1';
            inputQty.name = 'detail_quantite';
            inputQty.className = 'form-control';
            inputQty.required = true;
            inputQty.value = '1';
            inputQty.oninput = function() { calculerMontantLigne(row); };
            tdQty.appendChild(inputQty);
            row.appendChild(tdQty);

            var tdMontant = document.createElement('td');
            tdMontant.className = 'montant-cell';
            tdMontant.textContent = '0.00';
            row.appendChild(tdMontant);

            var tdDel = document.createElement('td');
            var btnDel = document.createElement('button');
            btnDel.type = 'button';
            btnDel.className = 'btn-remove';
            btnDel.textContent = 'X';
            btnDel.onclick = function() { row.remove(); calculerTotal(); };
            tdDel.appendChild(btnDel);
            row.appendChild(tdDel);

            tbody.appendChild(row);
        }

        function calculerMontantLigne(row) {
            var pu = parseFloat(row.querySelector('input[name="detail_prixUnitaire"]').value) || 0;
            var qty = parseInt(row.querySelector('input[name="detail_quantite"]').value) || 0;
            var montant = pu * qty;
            row.querySelector('.montant-cell').textContent = montant.toFixed(2);
            calculerTotal();
        }

        function calculerTotal() {
            var cells = document.querySelectorAll('.montant-cell');
            var total = 0;
            cells.forEach(function(cell) {
                total += parseFloat(cell.textContent) || 0;
            });
            document.getElementById('montantTotal').textContent = total.toFixed(2);
        }

        window.onload = function() {
            ajouterLigne();
        };
    </script>
</body>
</html>

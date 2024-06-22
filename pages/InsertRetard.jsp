<%@ page import="java.util.List" %>
<%@ page import="Personnel.Retard" %>
<%@ page import="Personnel.MyPoste" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/formulaire.css">

    <script src="asset/jquery.min.js"></script>

    <script>
        function loadPersonnels() {
            var posteSelect = document.getElementById("postes"); // Correction ici
            var posteChoisi = posteSelect.value;
            $.ajax({
                url: "get-personnels",
                type: "GET",
                data: { idPoste: posteChoisi },
                success: function(responseHtml) {
                    var personnels_select = document.getElementById("personnels");
                    personnels_select.innerHTML = "";
                    $.each(responseHtml, function(index, personnel) {
                        var option = document.createElement("option");
                        option.value = personnel.idPersonnel.toString();
                        option.text = personnel.nom;
                        personnels_select.appendChild(option);
                    });
                },
                error: function(xhr, status, error) {
                    console.error("Erreur lors de l'appel Ajax:", error);
                }
            });
        }

        window.onload = function() {
            var posteSelect = document.getElementById("postes"); // Correction ici
            posteSelect.addEventListener("change", function() {
                loadPersonnels();
            });
            loadPersonnels();
        };

    </script>
</head>

<body>
<div class="formulaire">

    <form action="InsertPresence" method="post">
        <div class="logo">
            <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
        </div>
        <h2>Insertion de presence</h2>
        <div>
            <p>Poste :</p>
            <select name="postes" id="postes">
                <%
                    List<MyPoste> listePoste = (List<MyPoste>) request.getAttribute("ListePoste");
                    for (int i = 0; i < listePoste.size(); i++) {
                %>
                    <option value="<%= listePoste.get(i).getIdPoste() %>"><%= listePoste.get(i).getPoste() %></option>
                <% } %>
            </select>
        </div>
        <div>
            <p>Personnel:</p>
            <select name="personnels" id="personnels">

            </select>
        </div>
        <div>
            <p>Heure :</p>
            <input type="time" name="heure"></p>
        </div>
        <div class="form-btn">
            <button type="submit" class="btn-valide" name="valider" value="e">Entre</button>
            <button type="submit" class="btn-valide" name="valider" value="s">Sortie</button>
            <a href="" class="btn-cancel">annuler</a>
        </div>
    </form>
</div>
</body>
</html>
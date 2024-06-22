<%-- 
    Document   : insert
    Created on : 24-May-2024, 21:18:09
    Author     : isaia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertion de dépense</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/formulaire.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css"  />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css"  />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css"  />
    </head>
    <body>
    <div class="formulaire">
        <div class="logo">
            <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
        </div>
        <h2>Insertion des dépenses</h2>
        <form action="Insertion_servlet" method="post">
            <div>
                <p>Description</p>
                <input type="text" name="description" required>
            </div>
            <div>
                <p>Montant</p>
                <input type="number" name="montant" required>
            </div>
            <div>
                <p>Date</p>
                <input type="date" name="date" required>
            </div>
            <div class="form-btn">
                <button type="submit" class="btn-valide">valider</button>
                <a href="" class="btn-cancel">annuler</a>
            </div>
        </form>
    </div>
    </body>
</html>

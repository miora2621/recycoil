<%@ page import="java.util.List" %>
<%@ page import="Personnel.Retard" %>
<%@ page import="Personnel.MyPoste" %>
<%@ page import="vente.Produit" %>
<%@page import="front_office.Personne"%>

<%
if(session.getAttribute("user")==null){
    response.sendRedirect("login.jsp");
}
Personne personne=(Personne)session.getAttribute("user");
if(personne.get_etat().equals("tsotra")){
        response.sendRedirect("crash.jsp");
    }
%>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
<div class="formulaire">
    <div class="logo">
        <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
    </div>
    <h2>Insertion de vente</h2>
    <form action="InsertVente" method="post">
        <div>
            <p>Produit </p>
            <select name="produit">
                <%
                    List<Produit> listeProduit = (List<Produit>) request.getAttribute("ListeProduit");
                    for (int i = 0; i < listeProduit.size(); i++) {
                %>
                    <option value="<%= listeProduit.get(i).getIdProduit() %>"><%= listeProduit.get(i).getLibelle() %> </option>
                <% } %>
            </select>
        </div>
        <div>
            <p>Date </p>
            <input type="date" name="date" required>
        </div>
        <div>
            <p>Quantite</p>
            <input type="number" name="quantite" required>
        </div>
        <div class="form-btn">
            <button type="submit" class="btn-valide">valider</button>
            <a href="" class="btn-cancel">annuler</a>
        </div>
    </form>
</div>
</body>
</html>
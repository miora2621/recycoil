<%@ page import="models.*" %>
<%@ page import="vente.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
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
    <title>Veuillez inserer vos propres choix</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/formulaire.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css">
</head>
<body>
    <div class="formulaire">
        <div class="logo">
            <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
        </div>
        <% 
        List<Produit> produits = (List<Produit>) request.getAttribute("allProduits");
        Production produit = (Production) request.getAttribute("produit");
        boolean isUpdate = produit != null;
        %>
        <h2><%= isUpdate ? "Modifier la production" : "Nouvelle production" %></h2>
        <form action="<%= isUpdate ? "./Product_formservlet?mode=u&id=" + produit.get_id() : "./Product_servlet" %>" method="post">
            <div>
                <p>Id produit:</p>
                <select name="id_production">
                    <% for (Produit p : produits) { %>
                        <option value="<%= p.getIdProduit() %>" <%= isUpdate && p.getIdProduit() == produit.get_id_produit() ? "selected" : "" %>>
                            <%= p.getLibelle() %>
                        </option>
                    <% } %>
                </select>
            </div>
            <div>
                <p>Date de la production:</p>
                <input type="date" name="date_production" value="<%= isUpdate ? produit.get_date_production() : "" %>">
            </div>
            <div>
                <p>Quantite:</p>
                <input type="number" name="amount" value="<%= isUpdate ? produit.get_quantite() : "" %>">
            </div>
            <div class="form-btn">
                <button type="submit" class="btn-valide">valider</button>
                <a href="" class="btn-cancel">annuler</a>
            </div>
        </form>
    </div>
</body>
</html>

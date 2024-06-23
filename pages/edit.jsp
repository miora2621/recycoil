<%-- 
    Document   : edit
    Created on : 25-May-2024, 08:04:43
    Author     : isaia
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="need.Depense" %>
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
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Depense</title>
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
        <h2>Depense</h2>
        <%
            Depense depense = (Depense) request.getAttribute("depense");
            if (depense != null) {
        %>
        <form action="edit_delete_servlet" method="post">
            <input type="hidden" name="idDepense" value="<%= depense.getId() %>">
            <div>
                <p>Description:</p>
                <input type="text" id="raison" name="raison" value="<%= depense.getRaison() %>" required>
            </div>
            <div>
                <p>Montant:</p>
                <input type="number" id="montant" name="montant" value="<%= depense.getMontant() %>" required>
            </div>
            <div>
                <p>Date:</p>
                <input type="date" id="dateDepense" name="dateDepense" value="<%= depense.getDateDepense() %>" required>
            </div>
            <div class="form-btn">
                <button type="submit" class="btn-valide">Modifier</button>
                <a href="" class="btn-cancel">annuler</a>
            </div>
        </form>
        <%
            } else {
        %>
        <p>No edituu</p>
        <%
            }
        %>
    </div>
</body>
</html>


<%@page import="java.util.List, models.*"%>
<%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/29/2024
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String libelle = "";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
        <h2>Matiere Premiere</h2>
        <form action="<%= request.getContextPath() %>/matierePremiereCRUD" method="post">
            <% if(request.getParameter("id")!=null){ 
                String id = request.getParameter("id");
                MatierePremiere matierePremieres = MatierePremiere.get_byId(Integer.parseInt(id));
                libelle = matierePremieres.get_libelle();
            %>    
                <input type="hidden" name="id" value="<%=id%>">

            <% } %>
            <div>
                <p>Libelle</p>
                <input type="text" name="libelle" value="<%=libelle %>" required>
            </div>
            <div>
                <p>Viscosite</p>
                <input type="number" name="viscosite" required>
            </div>
            <span>
                <p><input type="radio" name="method" value="insert"> Ajouter</p>
                <% if(request.getParameter("id")!=null){ %>
                    <p><input type="radio" name="method" value="update"> Modifier</p>    
                <% } %>
            </span>
            <div class="form-btn">
                <button type="submit" class="btn-valide">valider</button>
                <a href="" class="btn-cancel">annuler</a>
            </div>
        </form>
    </div>
</body>
</html>

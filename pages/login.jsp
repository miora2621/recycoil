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
<%
    if(request.getAttribute("message")!=null){
%>
<script>
    <% out.print("alert(\""+request.getAttribute("message").toString()+"\")");%>
</script>
<% } %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/login.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css"  />
</head>
<body>
    <div class="text-box">
        <div class="logo">
            <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
            <p>Recyclage d'huile a Madagascar pour les malagasy et par des malagasy</p>
        </div>
    </div>
    <div class="formulaire">
        <h2>BIENVENUE</h2>
        <form id="login_form" method="POST" action="LoginServlet">
            <p>
                <i class="fa fa-user"></i>
                <input type="text" id="email" name="email" placeholder="Adresse Email">
            </p>
            <p>
                <i class="fa fa-lock"></i>
                <input type="password" id="mot_de_passe" name="mot_de_passe" placeholder="Mot de Passe">
            </p>
            <button type="submit">Connecter</button>
        </form>
    </div>
</body>
</html>

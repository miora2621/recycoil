<%@page import="front_office.Personne"%>
<%
    if(session.getAttribute("user")==null){
        response.sendRedirect("login.jsp");
    }
    Personne usereee =(Personne)session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css"  />
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/home.css"  />
</head>
<body>
    <jsp:include page="templates/sideBar.jsp"/>
    <div class="section-right">
        <div class="text-message" style="margin-top: 200px;">
            <h1>BIENVENUE</h1>
            <h2><i id="email"><% out.print(usereee.get_id_personne()); %></i></h2>
            <p>Bienvenu <strong id="etat"><% out.print(usereee.get_etat());%></strong>, que voulez-vous faire aujourd'hui? </p>
        </div>
    </div>
</body>
</html>

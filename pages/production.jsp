<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Production</title>
    </head>
    <body>
        <h2>Filtrer les productions par date</h2>
        <form action="./SumQuantiteProductionServlet" method="post">
            <label for="date_min">Date Min:</label>
            <input type="date" id="date_min" name="date_min" required>
            <br>
            <label for="date_max">Date Max:</label>
            <input type="date" id="date_max" name="date_max" required>
            <br>
            <input type="submit" value="Filtrer">
        </form>
    </body>
</html>

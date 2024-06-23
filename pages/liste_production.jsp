<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="vente.*" %>
<%@ page import="java.sql.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Liste des produits</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/home.css"  />
</head>
<body>
    <jsp:include page="templates/sideBar.jsp"/>
    <div class="section-right">
        <section>
            <div class="title">
                <i class="fa fa-dolly-flatbed"></i>
                <h2>Production</h2>         
            </div>
            <form action="<%= request.getContextPath()%>/SumQuantiteProductionServlet" method="post">
                <div class="search-form">
                    <div class="search-div">
                        <span>
                            <label for="date_max">Date Max:</label>
                            <input class="search-item" type="date" id="date_max" name="date_max" required>
                        </span>            
                        <span>
                            <label for="date_min">Date Min:</label>
                            <input class="search-item" type="date" id="date_min" name="date_min" required>
                        </span>
                    </div>
                    <div class="search-button">
                        <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
            <div class="tableau">
                <table>
                    <thead>
                        <tr>
                            <th>Nom produit</th>
                            <th>Date de production</th>
                            <th>Quantite</th>
                            <th class="title-action">Action</th> 
                        </tr>                      
                    </thead>
                    <tbody>
                        <% 
                        List<Production> allProduct = (List<Production>)request.getAttribute("allProduct");
                        for (Production produitItem : allProduct) { %>
                        <tr>
                            <td class="table-item"><%= produitItem.get_libelle() %></td>
                            <td class="table-item"><%= produitItem.get_date_production() %></td>
                            <td class="table-item"><%= produitItem.get_quantite() %></td>
                            <!-- <td><a href="./Product_servlet?mode=d&id=<%= produitItem.get_id() %>">Supprimer</a></td>
                            <td><a href="./Product_formservlet?mode=u&id=<%= produitItem.get_id() %>">Update</a></td> -->
                            <td class="bt-action">
                                <a href="<%= request.getContextPath()%>/Product_formservlet?mode=u&id=<%= produitItem.get_id() %>" class="update">Modifier</a>
                                <a href="<%= request.getContextPath()%>/Product_servlet?mode=d&id=<%= produitItem.get_id() %>" class="delete">Supprimer</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</body>
</html>

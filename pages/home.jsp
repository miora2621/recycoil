<%-- 
    Document   : home
    Created on : 24-May-2024, 21:42:26
    Author     : isaia
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="need.Depense" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/home.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css"  />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css"  />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css"  />
    </head>
    <body>
       <jsp:include page="templates/sideBar.jsp"/>
    <div class="section-right">
        <header>
            <div class="search-form">
                <form action="FindDepenseServlet" method="get">
                    <label for="" class="lab">Recherche des dépenses selon les dates</label>
                  
                    <button type="submit"><i class="fa fa-search"></i></button>
                    <input type="date" name="date1">  et <input type="date" name="date2">
                </form>
            </div>
            <nav class="menu">
                <p>
                    <a href="InsertionServlet" class="bt"><i class="far fa-plus"></i> Ajouter</a>
                </p>
                <p>
                    <a href=""><i class="far fa-bell"></i></a>
                </p>
                <p>
                    <a href=""><i class="fa fa-power-off"></i></a>
                </p>
            </nav>
        </header>
        <section>
            <div class="title">
                <i class="fa fa-dolly-flatbed"></i>
                <h2>Dépense</h2>         
            </div>
            <div class="tableau">
                    <table>
                    <tr>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Montant</th>
                        <th>Action</th>
                    </tr>
                    <%
                        List<Depense> depenses = (List<Depense>) request.getAttribute("depenses");
                        if (depenses != null) {
                            for (Depense depense : depenses) {
                    %>
                    <tr>
                        <td><%= depense.getDateDepense() %></td>
                        <td><%= depense.getRaison() %></td>
                        <td><%= depense.getMontant() %></td>
                        <td>
                             <a href="edit_delete_servlet?mode=u&idDepense=<%= depense.getId() %>">Modifier</a> <!-- Edit -->
                            <a href="edit_delete_servlet?mode=d&idDepense=<%= depense.getId() %>">Supprimer</a> <!-- Delete -->
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="3">No data available</td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
                <br>
            <div class="tableau">
                <h3>Total Dépenses Mensuelles</h3>
                <table>
                    <tr>
                        <th>Année</th>
                        <th>Mois</th>
                        <th>Total Dépenses</th>
                    </tr>
                    <%
                        List<Map<String, Object>> monthlyTotals = (List<Map<String, Object>>) request.getAttribute("monthlyTotals");
                        if (monthlyTotals != null) {
                            for (Map<String, Object> total : monthlyTotals) {
                    %>
                    <tr>
                        <td><%= total.get("annee") %></td>
                        <td><%= total.get("mois") %></td>
                        <td><%= total.get("total_depense") %></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="3">No data available</td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </section>
          
    </div>
    </body>
</html>

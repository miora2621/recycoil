<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="need.Depense" %>
<%@ page import="need.Depense" %>
<%
    Double montant = (Double)request.getAttribute("montant");
%>

<!DOCTYPE html>
<html lang="en">
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
        <section>
            <div class="title">
                <i class="fa fa-dolly-flatbed"></i>
                <h2>Depense selon le mois</h2>         
            </div>
            <div class="tableau">
                <table>
                    <tr>
                        <th>Motifs</th>
                        <th>Date </th>
                        <th>Montant</th>
                    </tr>
                    <%
                        List<Depense> depenses = (List<Depense>) request.getAttribute("depenses");
                        if (depenses != null) {
                            for (Depense depense : depenses) {
                    %>
                    <tr>
                        <td class="table-item"><%= depense.getRaison() %></td>
                        <td class="table-item"><%= depense.getDateDepense() %></td>
                        <td class="table-item"><%= depense.getMontant() %></td>
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
                    <br> 
                    <p>Montant total entre ces 2 dates : <%= montant%></p>
            </div>
        </section>
    </div>    
</body>
</html>
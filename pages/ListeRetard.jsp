<%@ page import="java.util.List" %>
<%@ page import="Personnel.Retard" %>
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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/home.css">

    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
    <jsp:include page="templates/sideBar.jsp"/>
    <div class="section-right">
        <section>
            <div class="title">
                <i class="fa fa-clock"></i>
                <h2>Historique des retards</h2>
            </div>
            <form action="listeRetard" method="post">
                <div class="search-form">            
                    <div class="search-div">
                        <span>
                            <label for="date_max">Retards entre</label>
                            <input class="search-item" type="date" name="fin" <% if (request.getAttribute("fin")!=null){ %> value="<%= request.getAttribute("fin")%>" <%}%> required>
                        </span> 
                        <span>
                            <label> et</label>
                            <input class="search-item" type="date" name="debut" <% if (request.getAttribute("debut")!=null){ %> value="<%= request.getAttribute("debut") %>" <%}%> required>
                        </span>
                                  
    
                    </div>
                    <div class="search-button">
                        <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
            <div class="tableau">
                <% List<Retard> listeRetard = (List<Retard>) request.getAttribute("ListRetard");
                    if (request.getAttribute("ListRetard")!=null && listeRetard.size()!=0) { %>
                    <table>
                        <tr>
                            <th>ID Presonnel</th>
                            <th>Nom</th>
                            <th>poste</th>
                            <th>Date</th>
                            <th>Heure de retard</th>
                        </tr>
                        <% for (int i = 0; i < listeRetard.size(); i++) { %>
                            <tr>
                                <td class="table-item"><%= listeRetard.get(i).getIdPersonnel() %></td>
                                <td class="table-item"><%= listeRetard.get(i).getNom() %></td>
                                <td class="table-item"><%= listeRetard.get(i).getPoste() %></td>
                                <td class="table-item"><%= listeRetard.get(i).getDatePresence() %></td>
                                <td class="table-item"><%= listeRetard.get(i).getHeureDebut() %></td>

                            </tr>
                        <% } %>
                    </table>
                <% } %>
            </div>
        </section>
    </div>    
</body>
</html>
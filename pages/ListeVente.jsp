<%@ page import="java.util.List" %>
<%@ page import="Personnel.Retard" %>
<%@ page import="vente.Vente" %>
<%@ page import="java.util.Date" %>
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
            <i class="fa fa-store"></i>
            <h2>Historique des ventes</h2>
        </div>
        <form action="listeVente" method="post">
            <div class="search-form">
                <div class="search-div">
                    <span>
                        <label>Vente entre</label>
                        <input class="search-item" type="date" name="debut" <% if (request.getAttribute("debut")!=null){ %> value="<%= request.getAttribute("debut") %>" <%}%> required>
                    </span>            
                    <span>
                        <label>et</label>
                        <input class="search-item" type="date" name="fin" <% if (request.getAttribute("fin")!=null){ %> value="<%= request.getAttribute("fin")%>" <%}%> required>
                    </span>
                </div>
                <div class="search-button">
                    <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
                </div>
            </div>
        </form>
        <div class="tableau">
            <% List<Vente> listeVente = (List<Vente>) request.getAttribute("ListVente");
                if (listeVente!=null && listeVente.size()!=0) { %>
            <table>
                <tr>
                    <th>Libelle Produit</th>
                    <th>Viscosite</th>
                    <th>Quantite</th>
                    <th>Total</th>
                </tr>
                <% for (int i = 0; i < listeVente.size(); i++) { %>
                <tr>
                    <td class="table-item"><%= listeVente.get(i).getLibelle_produit() %></td>
                    <td class="table-item"><%= listeVente.get(i).getViscosite() %></td>
                    <td class="table-item"><%= listeVente.get(i).getQuantite() %></td>
                    <td class="table-item"><%= listeVente.get(i).getSomme() %></td>
                </tr>
                <% } %>
            </table>
            <% } %>
        </div>
    </section>
</div>
</body>
</html>
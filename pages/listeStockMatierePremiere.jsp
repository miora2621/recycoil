<%@page import="java.util.List, models.*"%>
<%
    List<StockMatierePremiere> stockMatierePremieres = (List<StockMatierePremiere>)request.getAttribute("stockMatierePremieres");
    List<MatierePremiere> matierePremieres = (List<MatierePremiere>)request.getAttribute("matierePremieres");
%>
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
</head>
<body>
    <jsp:include page="templates/sideBar.jsp"/>
    <div class="section-right">
        <section>
            <div class="title">
                <i class="fa fa-box-open"></i>
                <h2>Liste matiere premiere</h2>         
            </div>
            <form action="<%= request.getContextPath() %>/stockMatierePremiereController" method="post">
                <div class="search-form">
                    <div class="search-div">
                        <span>
                            <label for="earlier_date">Date entre</label>
                            <input class="search-item" type="date" name="date1" placeholder="min">
                        </span>            
                        <span>
                            <label for="lateer_date">et</label>
                            <input class="search-item" type="date" name="date2" placeholder="max">
                        </span>
                        <span>
                            <label for="date_min">Matiere premiere</label>
                            <select class="search-item" name="idMatierePremiere">
                                <option value="">Choisir</option>
                                <% for(MatierePremiere matierePremiere : matierePremieres) { %>
                                    <option value="<%= matierePremiere.get_id() %>"><%= matierePremiere.get_id()%></option>
                                <% } %>
                            </select>
                        </span>
                        <span>
                            <label for="lateer_date">Quantite entre</label>
                            <input class="search-item" type="number" name="quantite1" placeholder="min">
                        </span>
                        <span>
                            <label for="lateer_date">et</label>
                            <input class="search-item" type="number" name="quantite2" placeholder="max">
                        </span>
                    </div>
                    <div class="search-button">
                        <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
            <div class="tableau">
                <table>
                    <tr>
                        <th>Id stock</th>
                        <th>Id matiere premieres</th>
                        <th>Quantité</th>
                        <th>Date</th>
                        <th>Type</th>
                        <th class="title-action">Action</th>
                    </tr>
                    <% for(StockMatierePremiere stockMatierePremiere : stockMatierePremieres) { %>
                        <tr>
                            <td class="table-item"><%= stockMatierePremiere.get_id() %></td>
                            <td class="table-item"><%= stockMatierePremiere.get_id_matiere_premiere() %></td>
                            <td class="table-item"><%= stockMatierePremiere.get_Quantite() %></td>
                            <td class="table-item"><%= stockMatierePremiere.get_Date().toString() %></td>
                            <td class="table-item"><% if(stockMatierePremiere.get_mouvement() == 0) { out.println("Entree"); }else{ out.println("sortie"); } %></td>
                            <td class="bt-action">
                                <a href="<%= request.getContextPath() %>/Modif&AjoutStockMatierePremiere.jsp?id=<%=stockMatierePremiere.get_id() %>" class="update">Modifier</a>
                                <a href="<%= request.getContextPath() %>/stockMatierePremiereCRUD?method=delete&id=<%=stockMatierePremiere.get_id() %>" class="delete">Supprimer</a>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </section>
    </div>
</body>
</html>
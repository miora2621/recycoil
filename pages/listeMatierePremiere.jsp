<%@page import="java.util.List, models.*"%>
<%
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
            <form action="<%= request.getContextPath() %>/matierePremiereController" method="post">
                <div class="search-form">
                    <div class="search-div">
                        <span>
                            <label for="earlier_date">Libelle</label>
                            <input class="search-item" type="text" placeholder="nom matiere premiere" name="libelle">
                        </span>            
                        <span>
                            <label for="lateer_date">Viscosité entre</label>
                            <input class="search-item" type="number" name="viscosite1" placeholder="min">
                        </span>
                        <span>
                            <label for="date_min">et</label>
                            <input class="search-item" type="number" name="viscosite2" placeholder="max">
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
                        <th>Id matiere premiere</th>
                        <th>Viscosité</th>
                        <th>Libelle</th>
                        <th class="title-action">Action</th>
                    </tr>
                    <% for(MatierePremiere matierePremiere : matierePremieres) { %>
                        <tr>
                            <td class="table-item"><%= matierePremiere.get_id() %></td>
                            <td class="table-item"><%= matierePremiere.get_viscosite() %></td>
                            <td class="table-item"><%= matierePremiere.get_libelle() %></td>
                            <td class="bt-action">
                                <a href="<%= request.getContextPath() %>/Modif&AjoutMatierePremiere.jsp?id=<%= matierePremiere.get_id() %>" class="update">Modifier</a>
                                <a href="<%= request.getContextPath() %>/matierePremiereCRUD?method=delete&id=<%= matierePremiere.get_id() %>" class="delete">Supprimer</a>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </section>
    </div>
</body>
</html>
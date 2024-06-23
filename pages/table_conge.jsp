<%@page import="models.Conge,java.util.Vector,java.time.LocalDate" %>
<%@page import="front_office.Personne"%>

<%
if(session.getAttribute("user")==null){
    response.sendRedirect("login.jsp");
}
Personne personne=(Personne)session.getAttribute("user");
%>
<%
Vector<Conge> liste_conge = (Vector<Conge>) request.getAttribute("liste_conge");
    Vector<Personnel> liste_personnel=(Vector<Personnel>) request.getAttribute("liste_personnel");
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
        <header>
            <nav class="menu">
                    <%  if(!personne.get_etat().equals("tsotra")){  %>
                    <p>
                        <a href="formulaire_conge.jsp" class="bt"><i class="far fa-plus"></i> Ajouter</a>
                    </p>
                    <p>
                        <a href=""><i class="far fa-bell"></i></a>
                    </p>
                    <% } %>
                    <p>
                        <a href=""><i class="fa fa-power-off"></i></a>
                    </p>
                </nav>
            </header>
            <section>
                <div class="title">
                    <i class="fa fa-clock"></i>
                    <h2>Liste des Cong&eacute;s</h2>         
                </div>
                <%  if(!personne.get_etat().equals("tsotra")){  %>
                    <form action="find_liste_conge">
                        <div class="search-form">
                            <div class="search-div">
                        <span>
                            <label for="date_max">Date Max</label>
                            <input type="date" name="date_debut" class="search-item">
                        </span>            
                        <span>
                            <label for="date_min">Date Fin</label>
                            <input type="date" name="date_fin" class="search-item">
                        </span>
                        <span>
                            <label for="date_min">Personnel</label>
                            <select name="id" class="search-item">
                                <option value="">Choix du personnel</option>
                                <% for(Personnel personnel : liste_personnel){ %>
                                    <option value="<%= personnel.get_id_personnel() %>"><%= personnel.get_nom_personnel() %></option>
                                    <% } %>
                            </select>
                        </span>
                    </div>
                    <div class="search-button">
                        <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
            <% } %>
            <div class="tableau">
                <table>
                    <tr>
                        <th>Id Conge</th>
                        <th>Personnel</th>
                        <th>Date de debut</th>
                        <th>Duree</th>
                        <th>Date de retour</th>
                    </tr>
                    <% for(Conge conge : liste_conge) { %>
                        <tr>
                            <td class="table-item"><%= conge.get_id_conge() %></td>
                            <td class="table-item"><%= conge.get_nom_personnel() %></td>
                            <td class="table-item"><%= conge.get_date_debut_conge() %></td>
                            <td class="table-item"><%= conge.get_duree_conge() %> j</td>
                            <td class="table-item"><%= conge.get_date_fin_conge() %></td>
                        </tr>
                    <% } %>


                </table>
            </div>
        </section>
    </div>    
</body>
</html>
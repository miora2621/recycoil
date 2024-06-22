<%@ page import="absence.*,java.util.Vector"%>
<%
    Vector<AbsenceNonJustifie> lsAbsence=new Vector<AbsenceNonJustifie>();
    if(request.getAttribute("lsAbsence")!=null){
        lsAbsence=(Vector<AbsenceNonJustifie>)request.getAttribute("lsAbsence");
    }
    if(request.getAttribute("message")!=null){
            out.print("<script type=\"text/javascript\"/>alert(\""+(String)request.getAttribute("message") +"\");</script>");
    }
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
                <i class="fa fa-clock"></i>
                <h2>Absences non-justifiées</h2>         
            </div>
            <form action="absenceNJ" method="GET">
                <div class="search-form">
                    <div class="search-div">
                        <span>
                            <label for="earlier_date">Date Min</label>
                            <input type="date" name="earlier_date" class="search-item">
                        </span>            
                        <span>
                            <label for="lateer_date">Date Max</label>
                            <input type="date" name="lateer_date" class="search-item">
                        </span>
                        <span>
                            <label for="date_min">Nom</label>
                            <input type="text" name="nom" class="search-item">
                        </span>
                        <span>
                            <label for="date_min">Poste</label>
                            <input type="text" name="poste" class="search-item">
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
                        <th>matricule du personnel</th>
                        <th>nom </th>
                        <th>poste</th>
                        <th>date</th>
                    </tr>
                <% for(int i=0;i<lsAbsence.size();i++){%>
                    <tr>
                        <td class="table-item"><% out.print(lsAbsence.elementAt(i).getIdPersonnel()); %></td>
                        <td class="table-item"><% out.print(lsAbsence.elementAt(i).getNom()); %></td>
                        <td class="table-item"><% out.print(lsAbsence.elementAt(i).getPoste()); %></td>
                        <td class="table-item"><% out.print(lsAbsence.elementAt(i).getDate()); %></td>
                    </tr>
                <%}%>   

                </table>
            </div>
        </section>
    </div>    
</body>
</html>
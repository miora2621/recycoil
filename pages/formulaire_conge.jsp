<%
    String text = "";
    String color = "";
    if(request.getAttribute("resultat")!= null){
        text=(String) request.getAttribute("resultat");
        color=(String) request.getAttribute("color");
    } 

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/css/formulaire.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/asset/fontawesome/css/regular.css"  />
</head>
<body>
    <div class="formulaire">
        <div class="logo">
            <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
        </div>
        <h2>Conge</h2>
        <form action="insertion_conge" method="post">
            <div>
                <p>Personnel</p>
                <select name="personnel">
                    <option value="1">Rakoto</option>
                    <option value="2">Rabe</option>
                </select>
            </div>
            <div>
                <p>Date de debut du conge</p>
                <input type="date" name="date_debut" required>
            </div>
            <div>
                <p>Duree</p>

                <input type="number" placeholder="durée en jour(s)" name="duree" required>
            </div>
            <div>
                <p style="color:<%= color %> ;"><%= text %></p>
            </div>
            <div class="form-btn">
                <button type="submit" class="btn-valide">valider</button>
                <a href="insertion_conge" class="btn-cancel">voir liste</a>
            </div>
        </form>
    </div>
</body>
</html>
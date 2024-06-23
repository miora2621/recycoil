<%@page import="front_office.Personne"%>
<% if(session.getAttribute("user")==null ){
    response.sendRedirect("login.jsp");
}else{ %>
<%
 if(((Personne)session.getAttribute("user")).get_etat().equals("admin")){
%>
<div class="section-left">
    <div class="logo">
        <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
    </div>
    <nav class="menu">
        <a href="./Product_servlet">
            <div>
                <p>liste des productions</p>
                <i class="fa fa-clock"></i>
            </div>
        </a>
        <a href="./Product_formservlet">
            <div>
                <p>Inserer un produit</p>
                <i class="fa fa-clock"></i>
            </div>
        </a>
        <a href="production.jsp">
            <div>
                <p>filtre par date</p>
                <i class="fa fa-clock"></i>
            </div>
        </a>
         <a href="listeAbsenceNJ.jsp">
            <div>
                <p>Absence</p>
                <i class="fa fa-home"></i>
            </div>
        </a>
        <a href="insertion_conge">
            <div>
                <p>Conge</p>
                <i class="fa fa-home"></i>
            </div>
        </a>
       
        <a href="Insertion_servlet">
            <div>
                <p>Depense</p>
                <i class="fa fa-hand-holding-usd"></i>
            </div>
        </a>
        <a href="BeneficeServlet">
            <div>
                <p>Benefice</p>
                <i class="fa fa-donate"></i>
            </div>
        </a>
        <a href="<%= request.getContextPath() %>/matierePremiereController">
            <div>
                <p>Liste matiere premiere</p>
                <i class="fa fa-user-tie"></i>
            </div>
        </a>
        <a href="<%= request.getContextPath() %>/stockMatierePremiereController">
            <div>
                <p>Stock matiere premiere</p>
                <i class="fa fa-user-tie"></i>
            </div>
        </a>
        <a href="InsertVente">
        <div>
            <p>Insertion vente</p>
            <i class="fa fa-store"></i>
        </div>
    </a>
    <a href="ListeVente.jsp">
        <div>
            <p>Liste Ventte</p>
            <i class="fa fa-box-open"></i>
        </div>
    </a>
    <a href="InsertPresence">
        <div>
            <p>Presence</p>
            <i class="fa fa-user-tie"></i>
        </div>
    </a>
    <a href="ListeRetard.jsp">
        <div>
            <p>Historique Retards</p>
            <i class="fa fa-clock"></i>
        </div>
    </a>
    </nav>
</div>
<% }else if(((Personne)session.getAttribute("user")).get_etat().equals("tsotra")) { %>
    <div class="section-left">
        <div class="logo">
            <h1>R<i class="fa fa-tint" style="transform: rotate(180deg);"></i>cyc'<i class="fa fa-cog"></i>il</h1>
        </div>
        <nav class="menu">
    
             <a href="listeAbsenceNJ.jsp">
                <div>
                    <p>Absence</p>
                    <i class="fa fa-home"></i>
                </div>
            </a>
             <a href="insertion_conge">
                <div>
                    <p>Conge</p>
                    <i class="fa fa-home"></i>
                </div>
           
        <a href="ListeRetard.jsp">
            <div>
                <p>Historique Retards</p>
                <i class="fa fa-clock"></i>
            </div>
        </a>
        </nav>
    </div>
    <% } } %>
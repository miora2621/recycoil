package stock;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;
import need.*;
import vente.Vente;
import baseUtil.*;

public class Dashboard_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
///asina valeur par defaut le ixy de ny mois sy date misy zao sy sy ny 3 mois aloanzao 
            LocalDate currentDate = LocalDate.now();
            int mois = currentDate.getMonthValue();
            int annee = currentDate.getYear();
            Vector dash = new Vector();
            List depense = Depense.get_entre_deux_mois(annee, mois-3, annee, mois);
            List vente = Vente.get_vente_deux_mois(annee, annee, mois-3, mois);
            List<Benefice> benefice = Benefice.getBenefices(annee, annee, mois-3, mois, ConnexionPgsql.dbConnect());
            List total_vente = Vente.get_vente_deux_mois(annee, annee, mois-3, mois);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}

package controller;

import Personnel.MyPoste;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DateUtil;
import vente.Produit;
import vente.Vente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@WebServlet(name = "InsertVente", value = "/InsertVente")
public class InsertVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("ListeProduit",new Produit().listeProduit());
            RequestDispatcher dispatcher = req.getRequestDispatcher("InsertVente.jsp");
            dispatcher.forward(req,resp);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            String id_produit = req.getParameter("produit");
            String date_vente = req.getParameter("date");
            Integer quantite = Integer.valueOf(req.getParameter("quantite"));
            BigDecimal prix_actuel = Produit.prix_actuel(id_produit);
            Double total = prix_actuel.intValue()*quantite.doubleValue();

            Vente.insert_vente(id_produit,quantite,DateUtil.get_date_string(date_vente),prix_actuel,total);
            out.println("<script>alert('Vente succes');window.location.href = 'InsertVente';</script>");
            Integer id_vente = Vente.last_index_vente()+1;
            Vente.create_facture(id_vente,id_produit,date_vente,quantite,prix_actuel,total);
        }
        catch (Exception e)
        {
            out.println("<script>alert('"+e.getMessage()+"');window.location.href = 'InsertVente';</script>");
        }
    }
}

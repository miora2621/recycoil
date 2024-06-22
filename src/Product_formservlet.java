package servlet;

import vente.*;
import models.*;
import vente.Produit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import front_office.*;
import jakarta.servlet.*;

import jakarta.servlet.http.HttpSession;

public class Product_formservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
                try {
                List<Production> listeProduit = Production.get_allProducts();
                List<Produit> allProduits = Produit.listeProduit(); 
                request.setAttribute("allProduct", listeProduit);
                request.setAttribute("allProduits", allProduits);
            
                if (request.getParameter("mode") != null && request.getParameter("id") != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Production p = Production.getProductbyId(id);
                    request.setAttribute("produit", p);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("insert_production.jsp");
            
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }         
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
            dispatcher.forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
                String _id_produit = request.getParameter("id_production");
                String _date = request.getParameter("date_production");
                String _quantite = request.getParameter("amount");

                if (request.getParameter("mode") != null && request.getParameter("id") != null) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        int id_produit = Integer.parseInt(_id_produit);
                        int quantite = Integer.parseInt(_quantite);
                        Date date_production = java.sql.Date.valueOf(_date);

                        Production p = new Production(id, id_produit, date_production, quantite);
                        p.update_product();

                        List<Production> allProducts = Production.get_allProducts();
                        request.setAttribute("allProduct", allProducts);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("liste_production.jsp");
                        try {
                            dispatcher.forward(request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }     
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
                    dispatcher.forward(request, response);
            }
        
    }
}

package servlet;

import models.Production;
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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.*;

public class Product_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
       throws IOException ,ServletException{
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
            if (request.getParameter("mode") != null && request.getParameter("id") != null) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Production.delete_product(id);

                    List<Production> allProducts = Production.get_allProducts();
                    request.setAttribute("allProduct", allProducts);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("liste_production.jsp");
                
                        dispatcher.forward(request, response);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    List<Production> allProducts = Production.get_allProducts();
                    request.setAttribute("allProduct", allProducts);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("liste_production.jsp");
                
                    dispatcher.forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws IOException ,ServletException{
            if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
                 String _id_produit = request.getParameter("id_production");
                String _date = request.getParameter("date_production");
                String _quantite = request.getParameter("amount");

                try {
                    int id_produit = Integer.parseInt(_id_produit);
                    Date date_production = java.sql.Date.valueOf(_date);
                    int quantite = Integer.parseInt(_quantite);

                    Production.insert_product(id_produit, date_production, quantite);

                    List<Production> allProducts = Production.get_allProducts();
                    request.setAttribute("allProduct", allProducts);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("liste_production.jsp");

                    try {
                        dispatcher.forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }     
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
                    dispatcher.forward(request, response);
            }
        }
       
}
 
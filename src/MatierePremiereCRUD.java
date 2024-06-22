package servlet;

import java.io.*;
import java.text.DateFormat;
import java.sql.Date;

import models.MatierePremiere;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.RequestDispatcher;
import models.StockMatierePremiere;
import front_office.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpSession;
public class MatierePremiereCRUD extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
            response.setContentType("text/html");
            String method = request.getParameter("method");
            if (method.compareToIgnoreCase("delete")==0){
                try {
                    this.delete(request,response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }    
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
            response.setContentType("text/html");
            String method = request.getParameter("method");
            if (method.compareToIgnoreCase("insert")==0){
                try {
                    this.insert(request,response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (method.compareToIgnoreCase("update")==0){
                try {
                    this.update(request,response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }        
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
            dispatcher.forward(request, response);
        }
        
    }

    public void insert (HttpServletRequest request,HttpServletResponse response) throws Exception {

        double viscosite = Double.parseDouble(request.getParameter("viscosite"));
        String libelle = request.getParameter("libelle");

        MatierePremiere m = new MatierePremiere(libelle,viscosite);
        m.insert();
        response.sendRedirect(request.getContextPath()+"/matierePremiereController");

    }

    public void update (HttpServletRequest request,HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        double viscosite = Double.parseDouble(request.getParameter("viscosite"));
        String libelle = request.getParameter("libelle");

        MatierePremiere m = new MatierePremiere(id,libelle,viscosite);
        m.update();
        response.sendRedirect(request.getContextPath()+"/matierePremiereController");
    }

    public void delete (HttpServletRequest request,HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        MatierePremiere m = new MatierePremiere(id);
        new StockMatierePremiere().deleteByidMatiere(id);
        m.delete();
        response.sendRedirect(request.getContextPath()+"/matierePremiereController");
    }
}

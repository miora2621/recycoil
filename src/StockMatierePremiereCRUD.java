package servlet;

import models.MatierePremiere;
import models.StockMatierePremiere;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import front_office.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.*;
import java.io.IOException;

import java.sql.Date;
import java.text.DateFormat;

public class StockMatierePremiereCRUD extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
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
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Date dateM = Date.valueOf(request.getParameter("date"));
        int mouvement = Integer.parseInt(request.getParameter("mouvement"));

        StockMatierePremiere s = new StockMatierePremiere(idMatiere,quantite,dateM,mouvement);
        s.insert();
        response.sendRedirect(request.getContextPath()+"/stockMatierePremiereController");
    }

    public void update (HttpServletRequest request,HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Date dateM = Date.valueOf(request.getParameter("date"));
        int mouvement = Integer.parseInt(request.getParameter("mouvement"));

        StockMatierePremiere s = new StockMatierePremiere(id,idMatiere,quantite,dateM,mouvement);
        s.update();
        response.sendRedirect(request.getContextPath()+"/stockMatierePremiereController?idE="+id);
    }

    public void delete (HttpServletRequest request,HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        new StockMatierePremiere(id).delete();
        response.sendRedirect(request.getContextPath()+"/stockMatierePremiereController");
    }
}

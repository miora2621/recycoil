package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.sql.rowset.serial.SerialException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;



import models.Conge;
import models.Personnel;
public class Recherche_conge_servlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        try {
            String date_debut_conge=req.getParameter("date_debut");
            String date_fin_conge=req.getParameter("date_fin");
            String id_personnel=req.getParameter("id");
            Vector<Conge> list_conge=Conge.get_by_date(date_debut_conge,date_fin_conge,id_personnel);
            // Vector<Conge> list_conge=Conge.get_by_date(date_debut_conge,date_fin_conge,id);
            req.setAttribute("liste_conge",list_conge);
            Vector<Personnel> liste=Personnel.get_all();
            req.setAttribute("liste_personnel",liste);
            RequestDispatcher dispat = req.getRequestDispatcher("table_conge.jsp"); //modifier en fonction du nom de la page
            dispat.forward(req,res);
        } catch (Exception e) {
            res.sendRedirect("table_conge.jsp?err="+e.getMessage());
        }
        
    }
}

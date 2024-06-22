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

public class Conge_servlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        try {
            Vector<Conge> list_conge=Conge.get_all();
            req.setAttribute("liste_conge",list_conge);
            RequestDispatcher dispat = req.getRequestDispatcher("table_conge.jsp"); //modifier en fonction du nom de la page
            dispat.forward(req,res);
        } catch (Exception e) {
            res.sendRedirect("table_conge.jsp?err="+e.getMessage());
        }
        
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        //recuperation des donnees
        try {
            String id_personnel=req.getParameter("personnel");
            String date_debut=req.getParameter("date_debut");
            String duree=req.getParameter("duree");
            if (id_personnel.compareTo("")==0 || date_debut.compareTo("")==0 || duree.compareTo("")==0) {
                req.setAttribute("resultat","Toutes les cases doivent etre remplies");
                req.setAttribute("color","red");
                RequestDispatcher dispature = req.getRequestDispatcher("formulaire_conge.jsp"); //modifier en fonction du nom de la page
                dispature.forward(req,res);
            }
            if (LocalDate.parse(date_debut).isBefore(LocalDate.now())) {
                req.setAttribute("resultat","Date invalide, doit etre actuelle ou plus");
                req.setAttribute("color","red");
                RequestDispatcher dispature = req.getRequestDispatcher("formulaire_conge.jsp"); //modifier en fonction du nom de la page
                dispature.forward(req,res);
            }
            if (Integer.parseInt(duree)>20) {
                req.setAttribute("resultat","Pas plus de 20 jours en une demande");
                req.setAttribute("color","red");
                RequestDispatcher dispature = req.getRequestDispatcher("formulaire_conge.jsp"); //modifier en fonction du nom de la page
                dispature.forward(req,res);
            }
            Conge nouveau=new Conge( Integer.parseInt(id_personnel), LocalDate.parse(date_debut),Integer.parseInt(duree));
            int conge_restant=30 - nouveau.get_conge_restant();
            if (conge_restant < Integer.parseInt(duree)) {
                String text="insertion non valide car conge restant = "+conge_restant+" jour(s)";
                req.setAttribute("color","red");
                req.setAttribute("resultat",text);
                RequestDispatcher dispature = req.getRequestDispatcher("formulaire_conge.jsp"); //modifier en fonction du nom de la page
                dispature.forward(req,res);
            }else{
                nouveau.insert();
                req.setAttribute("resultat","Donnee insere");
                req.setAttribute("color","green");
                RequestDispatcher dispature = req.getRequestDispatcher("formulaire_conge.jsp"); //modifier en fonction du nom de la page
                dispature.forward(req,res);
            }
        } catch (Exception e) {
            req.setAttribute("resultat",e.getMessage());
            req.setAttribute("color","red");
            RequestDispatcher dispature = req.getRequestDispatcher("formulaire_conge.jsp"); //modifier en fonction du nom de la page
            dispature.forward(req,res);
        }
    }
}
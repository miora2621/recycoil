package servlet;

import jakarta.servlet.http.*;
import models.MatierePremiere;
import models.OperatorAndValue;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import front_office.*;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.*;

public class MatierePremiereController extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
            PrintWriter out = response.getWriter();
            String viscosite1 = "0";
            String viscosite2 = "999999";
            String libelle = "";
            String libelleParam = request.getParameter("libelle");
            String viscosite1Param = request.getParameter("viscosite1");
            if(libelleParam != null){
                libelle = libelleParam;
            }
            if (viscosite1Param != null && !viscosite1Param.isEmpty()) {
                try {
                    Integer.parseInt(viscosite1Param);
                    viscosite1 = viscosite1Param;
                } catch (NumberFormatException e) {
                    out.println("Veuillez entre une viscosite valide");
                }
            }
            String viscosite2Param = request.getParameter("viscosite2");
            if (viscosite2Param != null && !viscosite2Param.isEmpty()) {
                try {
                    Integer.parseInt(viscosite2Param);
                    viscosite2 = viscosite2Param;
                } catch (NumberFormatException e) {
                    out.println("Veuillez entre une viscosite valide");
                }
            }

            int quantiteI1 = Integer.parseInt(viscosite1);
            int quantiteI2 = Integer.parseInt(viscosite2);
            if(quantiteI1 > quantiteI2){
                viscosite1 = ""+quantiteI2+"";
                viscosite2 = ""+quantiteI1+"";
            }
            List<OperatorAndValue> datas = new ArrayList<>();
            try {
                datas.add(new OperatorAndValue("viscosite", ">=", viscosite1));
                datas.add(new OperatorAndValue("viscosite", "<=", viscosite2));
                datas.add(new OperatorAndValue("libelle", " LIKE ", "%"+libelle+"%"));
                List<MatierePremiere> matierePremieres = MatierePremiere.get_all_by_criteria(datas);
                request.setAttribute("matierePremieres", matierePremieres);
                request.getRequestDispatcher("listeMatierePremiere.jsp").forward(request, response);
            }catch(Exception e){
                out.println(e.getMessage());
            }             
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
            dispatcher.forward(request, response);
        }
        
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
            PrintWriter out = response.getWriter();
            try{
                List<MatierePremiere> matierePremieres = MatierePremiere.get_all();
                request.setAttribute("matierePremieres", matierePremieres);
                request.getRequestDispatcher("listeMatierePremiere.jsp").forward(request, response);
            }catch(Exception e){
                out.println(e.getMessage());
            }    
        }
        else{
             RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    

}
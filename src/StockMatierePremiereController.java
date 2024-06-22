package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.MatierePremiere;
import models.OperatorAndValue;
import models.StockMatierePremiere;
import front_office.*;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;

public class StockMatierePremiereController extends HttpServlet{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
             PrintWriter out = response.getWriter();
            String date1Param = request.getParameter("date1");
            String date2Param = request.getParameter("date2");
            String date1 = (date1Param != null && !date1Param.isEmpty()) ? date1Param : "1980-01-01";
            String date2 = (date2Param != null && !date2Param.isEmpty()) ? date2Param : "2090-01-01";
            String idMatierePremiere = request.getParameter("idMatierePremiere");
            String quantite1 = "0";
            String quantite2 = "999999";
            String quantite1Param = request.getParameter("quantite1");
            if (quantite1Param != null && !quantite1Param.isEmpty()) {
                try {
                    Integer.parseInt(quantite1Param);
                    quantite1 = quantite1Param;
                } catch (NumberFormatException e) {
                    out.println("Veuillez entre une quantite valide");
                }
            }
            String quantite2Param = request.getParameter("quantite2");
            if (quantite2Param != null && !quantite2Param.isEmpty()) {
                try {
                    Integer.parseInt(quantite2Param);
                    quantite2 = quantite2Param;
                } catch (NumberFormatException e) {
                    out.println("Veuillez entre une quantite valide");
                }
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1Obj = null;
            Date date2Obj = null;
            try {
                date1Obj = dateFormat.parse(date1);
                date2Obj = dateFormat.parse(date2);
            } catch (ParseException e) {
                out.println(e.getMessage());
            }
            int quantiteI1 = Integer.parseInt(quantite1);
            int quantiteI2 = Integer.parseInt(quantite2);
            if(quantiteI1 > quantiteI2){
                quantite1 = ""+quantiteI2+"";
                quantite2 = ""+quantiteI1+"";
            }
            if (date1Obj != null && date2Obj != null) {
                if (date1Obj.compareTo(date2Obj) > 0) {
                    Date temp = date1Obj;
                    date1Obj = date2Obj;
                    date2Obj = temp;
                }
                date1 = dateFormat.format(date1Obj);
                date2 = dateFormat.format(date2Obj);
            }
            List<OperatorAndValue> datas = new ArrayList<>();
            try {
                datas.add(new OperatorAndValue("quantite", ">=", quantite1));
                datas.add(new OperatorAndValue("quantite", "<=", quantite2));
                datas.add(new OperatorAndValue("date_stock_matiere_premiere", ">=", date1));
                datas.add(new OperatorAndValue("date_stock_matiere_premiere", "<=", date2));
                if(!idMatierePremiere.equals("")){
                    datas.add(new OperatorAndValue("id_matiere_premiere", "=", idMatierePremiere));
                }
                List<StockMatierePremiere> stockMatierePremieres = StockMatierePremiere.get_all_by_criteria(datas);
                List<MatierePremiere> matierePremieres = MatierePremiere.get_all();
                request.setAttribute("stockMatierePremieres", stockMatierePremieres);
                request.setAttribute("matierePremieres", matierePremieres);
                request.getRequestDispatcher("listeStockMatierePremiere.jsp").forward(request, response);
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
                List<StockMatierePremiere> stockMatierePremieres = StockMatierePremiere.get_all();
                List<MatierePremiere> matierePremieres = MatierePremiere.get_all();
                request.setAttribute("stockMatierePremieres", stockMatierePremieres);
                request.setAttribute("matierePremieres", matierePremieres);
                request.getRequestDispatcher("listeStockMatierePremiere.jsp").forward(request, response);
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

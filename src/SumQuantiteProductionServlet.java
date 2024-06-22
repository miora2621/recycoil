package servlet;

import models.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletResponse;
import front_office.*;
import jakarta.servlet.http.HttpSession;

public class SumQuantiteProductionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
         
            if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
                try {
                    String dateMinStr = request.getParameter("date_min");
                    String dateMaxStr = request.getParameter("date_max");

                    Date date_min = Date.valueOf(dateMinStr);
                    Date date_max = Date.valueOf(dateMaxStr);

                    List<Production> productions = Production.get_SumProductsWithDate(date_min, date_max);

                    request.setAttribute("productions", productions);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
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
}


package controller;

import Personnel.Retard;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DateUtil;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "listeRetard", value = "/listeRetard")
public class ListeRetard extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Date debut = DateUtil.get_date_string(req.getParameter("debut"));
        Date fin = DateUtil.get_date_string(req.getParameter("fin"));
        try {
            req.setAttribute("ListRetard",new Retard().retards_between_date(debut,fin));
            req.setAttribute("debut",DateUtil.formate_date(debut));
            req.setAttribute("fin",DateUtil.formate_date(fin));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("ListeRetard.jsp");
            dispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

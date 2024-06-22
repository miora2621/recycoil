package controller;

import Personnel.Retard;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DateUtil;
import vente.Vente;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "listeVente", value = "/listeVente")
public class ListeVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date debut = DateUtil.get_date_string(req.getParameter("debut"));
        Date fin = DateUtil.get_date_string(req.getParameter("fin"));
        try {
            req.setAttribute("ListVente",Vente.list_vente(debut,fin));
            req.setAttribute("debut",DateUtil.formate_date(debut));
            req.setAttribute("fin",DateUtil.formate_date(fin));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("ListeVente.jsp");
        dispatcher.forward(req,resp);
    }
}

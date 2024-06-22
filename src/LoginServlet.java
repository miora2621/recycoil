package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import front_office.Personne;

public class LoginServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {

        } catch (Exception e) {
            out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            String email = req.getParameter("email");
            String mot_de_passe = req.getParameter("mot_de_passe");

            Personne check = Personne.login(email, mot_de_passe);
            String message = "";
                        
            if (check.get_id_personne() <= 0) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                message = "Le login que vous avez entre n'existe pas... Veuillez contacter l'admin pour vous creer un login...";
                req.setAttribute("message", message);
                dispatcher.forward(req, res);
                
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("accueil.jsp");
                HttpSession session=req.getSession();
                session.setAttribute("user",check);
                dispatcher.forward(req, res);
            }
            
        } catch (Throwable e) {
            out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
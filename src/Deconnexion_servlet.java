package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sql.rowset.serial.SerialException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class Deconnexion_servlet extends HttpServlet {
      protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
          res.sendRedirect("login.jsp");
    }
}

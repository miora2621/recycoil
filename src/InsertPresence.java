package controller;

import Personnel.MyPersonnel;
import Personnel.MyPoste;
import Personnel.Retard;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@WebServlet(name = "InsertPresence", value = "/InsertPresence")
public class InsertPresence extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("ListePoste",new MyPoste().listePoste());
            RequestDispatcher dispatcher = req.getRequestDispatcher("InsertRetard.jsp");
            dispatcher.forward(req,resp);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

       String present_value = req.getParameter("valider");

       String timeInput = req.getParameter("heure");
       Time time = null;
       try{
           LocalTime localTime = LocalTime.parse(timeInput);
           time = Time.valueOf(localTime);
       }
       catch (Exception e)
       {

       }
       

       String idPersonnel = req.getParameter("personnels");


        System.out.println("Action: "+present_value);
       if (present_value.compareTo("e")==0)
       {
           try {
               new Retard().insert_entry(new Date(),time,idPersonnel);
               out.println("<script>alert('Success');window.location.href = 'InsertPresence';</script>");
           } catch (Exception e) {
               out.println("<script>alert('"+ e.getMessage() +"');window.location.href = 'InsertPresence';</script>");
           }
       }
       else {
           try {
               new Retard().insert_out(new Date(),time,idPersonnel);
               out.println("<script>alert('Success');window.location.href = 'InsertPresence';</script>");

           } catch (Exception e) {
               out.println("<script>alert('"+ e.getMessage() +"');window.location.href = 'InsertPresence';</script>");
           }
       }
    }
}

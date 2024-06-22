/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import baseUtil.ConnexionPgsql;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import need.Depense;
import java.util.List;
import front_office.*;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author isaia
 */
public class FindDepenseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FindDepenseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FindDepenseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
                    Date date1 = Date.valueOf(request.getParameter("date1"));
                    Date date2 = Date.valueOf(request.getParameter("date2"));
                    
                    try(Connection con = ConnexionPgsql.dbConnect()){
                        List<Depense> depense = Depense.findByDate(con, date1, date2);
                        double montant = Depense.get_sum_by_date(con, date1, date2);
                        request.setAttribute("depenses", depense);
                        request.setAttribute("montant", montant);

                        RequestDispatcher dispat = request.getRequestDispatcher("Search.jsp");
                        dispat.forward(request, response);
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
                        dispatcher.forward(request, response);
                }
             
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

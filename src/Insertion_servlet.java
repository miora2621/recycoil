/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import need.Depense;
import baseUtil.*;
import front_office.*;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author isaia
 */
public class Insertion_servlet extends HttpServlet {

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
            out.println("<title>Servlet InsertionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertionServlet at " + request.getContextPath() + "</h1>");
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
            RequestDispatcher dispat = request.getRequestDispatcher("/insert.jsp");
            dispat.forward(request, response);
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
               if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
                    String raison = request.getParameter("description");
                    double montant = Double.parseDouble(request.getParameter("montant"));
                    Date dateInsertion = Date.valueOf(request.getParameter("date"));
                    
                    Depense depense = new Depense(raison, montant, dateInsertion);
                    
                    try (Connection conn = ConnexionPgsql.dbConnect()) {
                        depense.insert(conn);
                        response.sendRedirect("DepenseServlet");
                    } catch (Exception e) {
                        e.printStackTrace();
                        RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("crash.jsp");
                        dispatcher.forward(request, response);
                }
        
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

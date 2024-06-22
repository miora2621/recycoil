/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import baseUtil.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import need.Depense;
import front_office.*;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author isaia
 */
public class edit_delete_servlet extends HttpServlet {

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
            out.println("<title>Servlet edit_delete_servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet edit_delete_servlet at " + request.getContextPath() + "</h1>");
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
                    String mode = request.getParameter("mode");
                    int idDepense = Integer.parseInt(request.getParameter("idDepense"));

                    if ("u".equals(mode)) {
                        // Forward to edit form
                        try (Connection conn = ConnexionPgsql.dbConnect()) {
                            Depense depense = Depense. readDepense(conn, idDepense); // You need to implement this method
                            request.setAttribute("depense", depense);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
                            dispatcher.forward(request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if ("d".equals(mode)) {
                        // Perform delete operation
                        try (Connection conn = ConnexionPgsql.dbConnect()) {
                            Depense.deleteDepense(conn, idDepense);
                            response.sendRedirect("DepenseServlet"); 
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
        // Handle update operation
       if(((Personne)(request.getSession().getAttribute("user"))).get_etat().equals("admin")){
            int idDepense = Integer.parseInt(request.getParameter("idDepense"));
            String raison = request.getParameter("raison");
            double montant = Double.parseDouble(request.getParameter("montant"));
            Date dateDepense = Date.valueOf(request.getParameter("dateDepense"));
            try (Connection conn = ConnexionPgsql.dbConnect()) {
                Depense.updateDepense(conn, idDepense, raison, montant, dateDepense);
                response.sendRedirect("DepenseServlet"); 
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
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

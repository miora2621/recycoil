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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import need.Benefice;
import front_office.*;
import jakarta.servlet.http.HttpSession;


/**
 *
 * @author isaia
 */
public class BeneficeServlet extends HttpServlet {

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
            out.println("<title>Servlet BeneficeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BeneficeServlet at " + request.getContextPath() + "</h1>");
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
                     try(Connection con= ConnexionPgsql.dbConnect()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2024, Calendar.MAY, 1);
                        Date date = calendar.getTime();
                        
                        double benefice = Benefice.calculerBenefice(date, con);
                        
                        request.setAttribute("benefice", benefice);
                        
                        RequestDispatcher dispatcher = request.getRequestDispatcher("benefice.jsp");
                        dispatcher.forward(request, response);
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
                PrintWriter out = response.getWriter();
            String strAnneeMin = request.getParameter("anneeMin");
            String strAnneeMax = request.getParameter("anneeMax");
            String strMoisMin = request.getParameter("moisMin");
            String strMoisMax = request.getParameter("moisMax");
            int anneeMin = 0;
            int anneeMax = 3999;
            int moisMin = 1;
            int moisMax = 12;
            if(!strAnneeMin.equals("")){
                anneeMin = Integer.parseInt(strAnneeMin);
            }
            if(!strAnneeMax.equals("")){
                anneeMax = Integer.parseInt(strAnneeMax);
            }
            if(!strMoisMin.equals("")){
                moisMin = Integer.parseInt(strMoisMin);
            }
            if(!strMoisMax.equals("")){
                moisMax = Integer.parseInt(strMoisMax);
            }
            Connection con = null;
            try {
                con= ConnexionPgsql.dbConnect();
                List<Benefice> benefices = Benefice.getBenefices(anneeMin, anneeMax, moisMin, moisMax, con);
                con.close();
                String beneficesJson = buildJsonResponse(benefices);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(beneficesJson);
                out.flush();
            } catch (Exception e) {
                out.println(e.getMessage());
                return;
            }
        }
        private String buildJsonResponse(List<Benefice> benefices) {
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < benefices.size(); i++) {
                Benefice benefice = benefices.get(i);
                json.append("{");
                json.append('"').append("annee").append('"').append(":"+benefice.getAnnee()).append(",");
                json.append('"').append("mois").append('"').append(":"+benefice.getMois()).append(",");
                json.append('"').append("benefice").append('"').append(":"+benefice.getBenefice());
                json.append("}");
                if (i < benefices.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            return json.toString();
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

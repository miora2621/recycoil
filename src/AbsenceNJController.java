package absence;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.io.*;


public class AbsenceNJController extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try
		{  
            String poste =request.getParameter("poste");
            String nom =request.getParameter("nom");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format de date du champ date
            Date earlier_date=new Date(dateFormat.parse(request.getParameter("earlier_date")).getTime());
            Date later_date=new Date(dateFormat.parse(request.getParameter("lateer_date")).getTime());
			Vector<AbsenceNonJustifie> lsAbsence=AbsenceNonJustifie.getAbsence(earlier_date,later_date,nom,poste);
			request.setAttribute("lsAbsence",lsAbsence);				
            RequestDispatcher dispat = request.getRequestDispatcher("/listeAbsenceNJ.jsp");				
            dispat.forward(request,response);
		}
	    catch(Exception e){
            RequestDispatcher dispat = request.getRequestDispatcher("/listeAbsenceNJ.jsp");	
            request.setAttribute("message",e.getMessage());
            dispat.forward(request,response);	
	 		e.printStackTrace();
	    }	
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        			
	}
}
package absence;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.io.*;


public class NbAbsencePDController extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try
		{  
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format de date du champ date
            Date earlier_date=new Date(dateFormat.parse(request.getParameter("earlier_date")).getTime());
            Date later_date=new Date(dateFormat.parse(request.getParameter("lateer_date")).getTime());

            out.print(NbAbsenceParMois.getNbAbsParMoisJSON(earlier_date,later_date));

            out.close();

		}
	    catch(Exception e){
	 		e.printStackTrace();
	    }	
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        			
	}
}
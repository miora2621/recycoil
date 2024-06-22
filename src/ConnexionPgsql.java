package baseUtil;

import java.sql.*;

public class ConnexionPgsql{
	public static Connection dbConnect()
	{
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/recycoil";
			String user = "postgres";
			String mdp = "Milliardaire2621";
			Connection con = DriverManager.getConnection(url, user, mdp);
			conn = con;
		
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}
}
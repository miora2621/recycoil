package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToPostgrel {
    public static Connection getConnectToBase(){
        String url = "jdbc:postgresql://localhost:5432/recycoil";
    
        Connection connex=null;
        try {
            Class.forName("org.postgresql.Driver");
            connex=DriverManager.getConnection(url, "postgres", "Dbamanager1");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connex;
    }
}

package models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;
import java.sql.ResultSet;
// import JDBC.ConnectionToPostgrel;
import baseUtil.ConnexionPgsql;
import models.Conge;
public class Personnel {
    int id_personnel;
    String nom_personnel;

    public Personnel(int id_personnel,String nom_personnel){
        set_id_personnel(id_personnel);
        set_nom_personnel(nom_personnel);
    }

    public void set_id_personnel(int id_personnel){
        this.id_personnel=id_personnel;
    }
    public void set_nom_personnel(String nom_personnel){
        this.nom_personnel=nom_personnel;
    }

    public int get_id_personnel(){
        return this.id_personnel;
    }
    public String get_nom_personnel(){
        return this.nom_personnel;
    }

    public static Vector<Personnel> get_all(){
        Vector<Personnel> list_personnel=new Vector<Personnel>();
        try {
            
       
          Connection connex= ConnexionPgsql.dbConnect();
       Statement st = connex.createStatement();
      ResultSet rs = st.executeQuery("select id_Personnel,nom from personnel");
      while (rs.next()){
        int id_personnel=rs.getInt(1);
        String nom_personnel=rs.getString(2);
        Personnel personnel=new Personnel(id_personnel, nom_personnel);
        list_personnel.add(personnel);
      }
      rs.close();
      st.close();
      connex.close();
    } catch (Exception e) {
        
    }
    finally{
        return list_personnel;

    }
    }
}

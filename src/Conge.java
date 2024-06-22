package models;
import java.time.LocalDate;
import java.util.Vector;
import java.sql.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
// import JDBC.ConnectionToPostgrel;
import baseUtil.ConnexionPgsql;

public class Conge{ 

    int id_conge;
    int id_personnel;
    String nom_personnel;
    LocalDate date_debut_conge;
    LocalDate date_fin_conge;
    int duree_conge;

//constructor   
//for select 
    public  Conge(int id_conge,int id_personnel,String nom_personnel,LocalDate date_debut_conge,int duree){
        set_id_conge(id_conge);
        set_id_personnel(id_personnel);
        set_nom_personnel(nom_personnel);
        set_date_debut_conge(date_debut_conge);
        set_duree_conge(duree);
    }

    public int get_conge_restant() throws Exception{
        Connection connex= ConnexionPgsql.dbConnect();
        Statement st = connex.createStatement();
        String requete="select sum(duree_conge) as duree_total from conge where Extract(year from date_debut_conge) = "+LocalDate.now().getYear()+" and id_personnel="+get_id_personnel();
      ResultSet rs= st.executeQuery(requete);
      if (rs.next()) {
          int total=  rs.getInt(1);
          rs.close();
          st.close();
          connex.close();
        //   System.out.println(total); 
          return total;
        }
        return 0;
    }

//for insert
    public  Conge(int id_personnel,LocalDate date_debut_conge,int duree){
        set_id_personnel(id_personnel);
        set_date_debut_conge(date_debut_conge);
        set_duree_conge(duree);
    }
    public void insert()throws Exception{
        //modifier en fonction de la classe d'obtention de connection vers la base
        Connection connex= ConnexionPgsql.dbConnect();
        Statement st = connex.createStatement();
        String donnee="("+get_id_personnel()+",'"+get_date_debut_conge()+"',"+get_duree_conge()+")";
        st.executeUpdate("INSERT INTO conge (id_personnel, date_debut_conge, duree_conge) VALUES " + donnee);
        st.close();
      connex.close();
    }
    public void update()throws Exception{
        //modifier en fonction de la classe d'obtention de connection vers la base
        Connection connex= ConnexionPgsql.dbConnect();
        Statement st = connex.createStatement();
        String donnee=" set id_personnel="+get_id_personnel()+", date_debut_conge='"+get_date_debut_conge()+"', duree_conge="+get_duree_conge()+" where id_conge="+get_id_conge();
        st.executeUpdate("Update conge" + donnee);
        st.close();
      connex.close();
    }
    public void delete()throws Exception{
        //modifier en fonction de la classe d'obtention de connection vers la base
        Connection connex= ConnexionPgsql.dbConnect();
        Statement st = connex.createStatement();
        String donnee=" where id_conge="+get_id_conge();
        st.executeUpdate("delete from conge" + donnee);
        st.close();
      connex.close();
    }
    
    public static Vector<Conge> get_all()throws Exception{
    //modifier en fonction de la classe d'obtention de connection vers la base

      Connection connex= ConnexionPgsql.dbConnect();
       Statement st = connex.createStatement();
      ResultSet rs = st.executeQuery("select * from v_conge");
        Vector<Conge> list_conge=new Vector<Conge>();
      while (rs.next()){
        int id_conge=rs.getInt(1);
        int id_personnel=rs.getInt(2);
        String nom_personnel=rs.getString(3);
        // System.out.println(nom_personnel);
        Date date_intermediaire= rs.getDate(4);
            LocalDate date_debut_conge= date_intermediaire.toLocalDate();
        int duree_conge = rs.getInt(5);
        Conge new_conge= new Conge(id_conge,id_personnel,nom_personnel,date_debut_conge,duree_conge);
        list_conge.add(new_conge);
      }
      rs.close();
      st.close();
      connex.close();
      return list_conge;
    }
    public static Vector<Conge> get_by_date(String date_debut,String date_fin,String id_personnel_string)throws Exception{
    //modifier en fonction de la classe d'obtention de connection vers la base

      Connection connex= ConnexionPgsql.dbConnect();
       Statement st = connex.createStatement();
       String requete="select * from v_conge";
       if (date_debut.compareTo("")!=0) {
        requete=requete+" where date_debut_conge>='"+date_debut+"' and date_fin_conge>='"+date_debut+"'";
        if (date_fin.compareTo("")!=0) {
            requete="select * from v_conge where ((date_debut_conge<='"+date_debut+"'"+" and date_fin_conge>='"+date_fin+"') ";
            requete=requete+" or (date_debut_conge<='"+date_debut+"'"+" and date_fin_conge<='"+date_fin+"' and date_fin_conge>='"+date_debut+"')";
            requete=requete+" or (date_debut_conge>='"+date_debut+"'"+" and date_fin_conge>='"+date_fin+"' and date_debut_conge<='"+date_fin+"'))";
            if (id_personnel_string.compareTo("")!=0) {
                requete=requete+" and id_personnel="+id_personnel_string;
            }
        }else if(id_personnel_string.compareTo("")!=0 && date_fin.compareTo("")==0){
            requete=requete+" and id_personnel="+id_personnel_string;
        }
       }else if(date_fin.compareTo("")!=0 && date_debut.compareTo("")==0){
        requete=requete+" where (date_fin_conge<='"+date_fin+"' or date_debut_conge<='"+date_fin+"')";
        if (id_personnel_string.compareTo("")!=0) {
            requete=requete+" and id_personnel="+id_personnel_string;
        }
       }else if(id_personnel_string.compareTo("")!=0 && date_fin.compareTo("")==0 && date_debut.compareTo("")==0){
        requete=requete+" where id_personnel="+id_personnel_string;
       }
       requete=requete+" limit 20";
      ResultSet rs = st.executeQuery(requete);
        Vector<Conge> list_conge=new Vector<Conge>();
      while (rs.next()){
        int id_conge=rs.getInt(1);
        int id_personnel=rs.getInt(2);
        String nom_personnel=rs.getString(3);
        // System.out.println(nom_personnel);
        Date date_intermediaire= rs.getDate(4);
            LocalDate date_debut_conge= date_intermediaire.toLocalDate();
        int duree_conge = rs.getInt(5);
        Conge new_conge= new Conge(id_conge,id_personnel,nom_personnel,date_debut_conge,duree_conge);
        list_conge.add(new_conge);
      }
      rs.close();
      st.close();
      connex.close();
      return list_conge;
    }

    
//getter
    public int get_id_conge(){
        return id_conge;
    }
    public int get_id_personnel(){
        return id_personnel;
    }
    public String get_nom_personnel(){
        return nom_personnel;
    }
    public LocalDate get_date_debut_conge(){
        return date_debut_conge;
    }
    public LocalDate get_date_fin_conge(){
        return date_fin_conge;
    }
    public int get_duree_conge(){
        return duree_conge;
    }
//setter    
    public void set_id_conge(int id_conge){
        this.id_conge=id_conge;
    }
    public void set_id_personnel(int id_personnel){
        this.id_personnel=id_personnel;
    }
    public void set_nom_personnel(String nom_personnel){
        this.nom_personnel=nom_personnel;
    }
    public void set_date_debut_conge(LocalDate date_debut_conge){
        
        this.date_debut_conge=date_debut_conge;
    }
    public void set_duree_conge(int duree){
        this.duree_conge=duree;
        this.date_fin_conge=date_debut_conge.plusDays(duree);
    }
}
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import baseUtil.ConnexionPgsql;

public class MatierePremiere{
    private int id;
    private String libelle;
    private double viscosite;

    public int get_id() {
        return id;
    }
    public void set_id(int id) {
        this.id = id;
    }
    public String get_libelle() {
        return libelle;
    }
    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }
    public double get_viscosite() {
        return viscosite;
    }
    public void set_viscosite(double viscosite) {
        this.viscosite = viscosite;
    }

    public MatierePremiere(){

    }   
    public MatierePremiere(int id, String libelle, double viscosite){
        this.set_id(id);
        this.set_libelle(libelle);
        this.set_viscosite(viscosite);
    }

    public MatierePremiere(String libelle, double viscosite) {
        this.libelle = libelle;
        this.viscosite = viscosite;
    }

    public MatierePremiere(int id) {
        this.id = id;
    }

    public void insert () throws Exception {
        Connection connection = ConnexionPgsql.dbConnect();
        PreparedStatement stmt=null;
        try{
            String sql = "INSERT INTO matiere_premiere (libelle,viscosite) VALUES (?,?)";
            stmt=connection.prepareStatement(sql);
            stmt.setString(1,this.get_libelle());
            stmt.setDouble(2,this.get_viscosite());

            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if (stmt!=null)stmt.close();
            if (connection!=null)connection.close();
        }
    }

    public void update () throws Exception {
        Connection connection = ConnexionPgsql.dbConnect();
        PreparedStatement stmt=null;
        try{
            String sql = "UPDATE matiere_premiere SET libelle=?,viscosite=? WHERE id_matiere_premiere=?";
            stmt=connection.prepareStatement(sql);
            stmt.setString(1,this.get_libelle());
            stmt.setDouble(2,this.get_viscosite());
            stmt.setInt(3,this.get_id());

            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if (stmt!=null)stmt.close();
            if (connection!=null)connection.close();
        }
    }

    public void delete () throws Exception {
        Connection connection = ConnexionPgsql.dbConnect();
        PreparedStatement stmt=null;
        try{
            // Delete StockProduit
            this.delete_stockProduit(this.get_id());

            // Delete Produit
            String sql = "DELETE FROM produit WHERE id_matiere_premiere=?";
            stmt=connection.prepareStatement(sql);
            stmt.setInt(1,this.get_id());
            stmt.executeUpdate();

            // Delete Matiere Premiere
            sql = "DELETE FROM matiere_premiere WHERE id_matiere_premiere=?";
            stmt=connection.prepareStatement(sql);
            stmt.setInt(1,this.get_id());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if (stmt!=null)stmt.close();
            if (connection!=null)connection.close();
        }
    }

    public void delete_stockProduit(int idMatiere)throws Exception{
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try{
            String sql = "SELECT * FROM produit WHERE id_matiere_premiere="+idMatiere;
            con = ConnexionPgsql.dbConnect();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id_produit");
                sql = "DELETE FROM stock_produit WHERE id_produit=?";
                PreparedStatement stmt=con.prepareStatement(sql);
                stmt.setInt(1,id);
                stmt.executeUpdate();

                sql = "DELETE FROM vente WHERE id_produit=?";
                stmt=con.prepareStatement(sql);
                stmt.setInt(1,id);
                stmt.executeUpdate();
                
                 // Delete Production
                sql = "DELETE FROM production WHERE id_produit=?";
                stmt=con.prepareStatement(sql);
                stmt.setInt(1,id);
                stmt.executeUpdate();
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(res != null){res.close();}
            if(st != null){st.close();}
            if(con != null){con.close();}
        }
    }

    public static MatierePremiere get_byId(int id)throws Exception{
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        MatierePremiere matierePremieres = null;
        try{
            String sql = "SELECT * FROM matiere_premiere WHERE id_matiere_premiere="+id;
            con = ConnexionPgsql.dbConnect();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                String libelle = res.getString("libelle");
                double viscosite = res.getDouble("viscosite");
                matierePremieres = new MatierePremiere(id, libelle, viscosite);
            }
            return matierePremieres;
        }catch(Exception e){
            throw e;
        }finally{
            if(res != null){res.close();}
            if(st != null){st.close();}
            if(con != null){con.close();}
        }
    }

    public static List<MatierePremiere> get_all()throws Exception{
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        List<MatierePremiere> matierePremieres = new Vector<>();
        try{
            String sql = "SELECT * FROM matiere_premiere";
            con = ConnexionPgsql.dbConnect();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id_matiere_premiere");
                String libelle = res.getString("libelle");
                double viscosite = res.getDouble("viscosite");
                matierePremieres.add(new MatierePremiere(id, libelle, viscosite));
            }
            return matierePremieres;
        }catch(Exception e){
            throw e;
        }finally{
            if(res != null){res.close();}
            if(st != null){st.close();}
            if(con != null){con.close();}
        }
    }
    public static List<MatierePremiere> get_all_by_criteria(List<OperatorAndValue> datas)throws Exception{
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        List<MatierePremiere> matierePremieres = new Vector<>();
        try{
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM matiere_premiere WHERE 1=1");
            builder.append(Utils.refactQuery(datas));
            String sql = builder.toString();
            con = ConnexionPgsql.dbConnect();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id_matiere_premiere");
                String libelle = res.getString("libelle");
                double viscosite = res.getDouble("viscosite");
                matierePremieres.add(new MatierePremiere(id, libelle, viscosite));
            }
            return matierePremieres;
        }catch(Exception e){
            throw e;
        }finally{
            if(res != null){res.close();}
            if(st != null){st.close();}
            if(con != null){con.close();}
        }
    }

}
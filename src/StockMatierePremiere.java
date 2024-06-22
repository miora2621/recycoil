package models;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import baseUtil.ConnexionPgsql;

public class StockMatierePremiere{
    private int id;
    private int id_matiere_premiere;
    private int quantite;
    private Date date;
    private int mouvement;

    public int get_id() {
        return id;
    }
    public void set_id(int id) {
        this.id = id;
    }
    public int get_id_matiere_premiere() {
        return id_matiere_premiere;
    }
    public void set_id_matiere_premiere(int id_matiere_premiere) {
        this.id_matiere_premiere = id_matiere_premiere;
    }
    public int get_Quantite() {
        return quantite;
    }
    public void set_quantite(int quantite) {
        this.quantite = quantite;
    }
    public Date get_Date() {
        return date;
    }
    public void set_date(Date date) {
        this.date = date;
    }
    public int get_mouvement() {
        return mouvement;
    }
    public void set_mouvement(int mouvement) {
        this.mouvement = mouvement;
    }

    
    public StockMatierePremiere(){}

    public StockMatierePremiere(int id, int id_matiere_premiere, int quantite, Date date, int mouvement){
        this.set_id(id);
        this.set_id_matiere_premiere(id_matiere_premiere);
        this.set_quantite(quantite);
        this.set_date(date);
        this.set_mouvement(mouvement);
    }

    public StockMatierePremiere(int id_matiere_premiere, int quantite, Date date, int mouvement) {
        this.id_matiere_premiere = id_matiere_premiere;
        this.quantite = quantite;
        this.date = date;
        this.mouvement = mouvement;
    }

    public StockMatierePremiere(int id) {
        this.id = id;
    }

    public void insert () throws Exception {
        Connection connection = ConnexionPgsql.dbConnect();
        PreparedStatement stmt=null;
        try{
            String sql = "INSERT INTO stock_matiere_premiere (id_matiere_premiere,quantite,date_stock_matiere_premiere,mouvement) VALUES (?,?,?,?)";
            stmt=connection.prepareStatement(sql);
            stmt.setInt(1,this.get_id_matiere_premiere());
            stmt.setInt(2,this.get_Quantite());
            stmt.setDate(3,this.get_Date());
            stmt.setInt(4,this.get_mouvement());

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
            String sql = "UPDATE stock_matiere_premiere SET id_matiere_premiere=?,quantite=?,date_stock_matiere_premiere=?,mouvement=? WHERE id_stock_matiere_premiere=?";
            stmt=connection.prepareStatement(sql);
            stmt.setInt(1,this.get_id_matiere_premiere());
            stmt.setInt(2,this.get_Quantite());
            stmt.setDate(3,this.get_Date());
            stmt.setInt(4,this.get_mouvement());
            stmt.setInt(5,this.get_id());
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
            String sql = "DELETE FROM stock_matiere_premiere WHERE id_stock_matiere_premiere=?";
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

    public void deleteByidMatiere (int id_matiere_premiere) throws Exception {
        Connection connection = ConnexionPgsql.dbConnect();
        PreparedStatement stmt=null;
        try{
            String sql = "DELETE FROM stock_matiere_premiere WHERE id_matiere_premiere=?";
            stmt=connection.prepareStatement(sql);
            stmt.setInt(1,id_matiere_premiere);

            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if (stmt!=null)stmt.close();
            if (connection!=null)connection.close();
        }
    }


    public static List<StockMatierePremiere> get_all()throws Exception{
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        List<StockMatierePremiere> stockMatierePremieres = new Vector<>();
        try{
            String sql = "SELECT * FROM stock_matiere_premiere";
            con = ConnexionPgsql.dbConnect();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id_stock_matiere_premiere");
                int id_matiere_premiere = res.getInt("id_matiere_premiere");
                int quantite = res.getInt("quantite");
                Date date = res.getDate("date_stock_matiere_premiere");
                int mouvement = res.getInt("mouvement");
                stockMatierePremieres.add(new StockMatierePremiere(id, id_matiere_premiere, quantite, date, mouvement));
            }
            return stockMatierePremieres;
        }catch(Exception e){
            throw e;
        }finally{
            if(res != null){res.close();}
            if(st != null){st.close();}
            if(con != null){con.close();}
        }
    }

    public static List<StockMatierePremiere> get_all_by_criteria(List<OperatorAndValue> datas)throws Exception{
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        List<StockMatierePremiere> stockMatierePremieres = new Vector<>();
        try{
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM stock_matiere_premiere WHERE 1=1");
            builder.append(Utils.refactQuery(datas));
            String sql = builder.toString();
            con = ConnexionPgsql.dbConnect();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id_stock_matiere_premiere");
                int id_matiere_premiere = res.getInt("id_matiere_premiere");
                int quantite = res.getInt("quantite");
                Date date = res.getDate("date_stock_matiere_premiere");
                int mouvement = res.getInt("mouvement");
                stockMatierePremieres.add(new StockMatierePremiere(id, id_matiere_premiere, quantite, date, mouvement));
            }
            return stockMatierePremieres;
        }catch(Exception e){
            throw e;
        }finally{
            if(res != null){res.close();}
            if(st != null){st.close();}
            if(con != null){con.close();}
        }
    }
}
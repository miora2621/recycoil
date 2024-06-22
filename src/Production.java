package models;

import java.sql.*;
import baseUtil.*;
import baseUtil.*;

import java.util.ArrayList;
import java.util.List;

public class Production {
    int id;
    int id_produit;
    String libelle;
    Date date_production;
    int quantite;

    public Production(int id, int id_produit, Date date_production, int quantite) {
        this.id = id;
        this.id_produit = id_produit;
        this.date_production = date_production;
        this.quantite = quantite;
    }

    public Production(int id,String libelle, Date date_production, int quantite) {
        this.id = id;
        this.libelle = libelle;
        this.date_production = date_production;
        this.quantite = quantite;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public int get_id_produit() {
        return id_produit;
    }

    public void set_id_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String get_libelle() {
        return libelle;
    }

    public void set_libelle(String libelle) {
        this.libelle = libelle;
    }

    public Date get_date_production() {
        return date_production;
    }

    public void set_date_production(Date date_production) {
        this.date_production = date_production;
    }

    public int get_quantite() {
        return quantite;
    }

    public void set_quantite(int quantite) {
        this.quantite = quantite;
    }

    // INSERT
    public static void insert_product(int id_produit, Date date_production, int quantite)throws Exception {
        String sql = "INSERT INTO production (id_produit, date_production, quantite) VALUES (?, ?, ?)";
         Connection con=null;
         PreparedStatement preparedStatement=null;
        try {
            con =ConnexionPgsql.dbConnect();
            preparedStatement = con.prepareStatement(sql);
               
            preparedStatement.setInt(1, id_produit);
            preparedStatement.setDate(2, new java.sql.Date(date_production.getTime()));
            preparedStatement.setInt(3, quantite);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        finally{
            con.close();
            preparedStatement.close();
        }
    }

    // Method to get a product by ID
    public static Production getProductById(int id)throws Exception {
        String sql = "SELECT * FROM production WHERE id = ?";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs =null;
        try {
            con =ConnexionPgsql.dbConnect();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int _id = rs.getInt("id");
                int id_produit = rs.getInt("id_produit");
                Date date_production = rs.getDate("date_production");
                int quantite = rs.getInt("quantite");

                return new Production(_id, id_produit, date_production, quantite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
         finally{
            con.close();
            preparedStatement.close();
            rs.close();
        }
        return null;
    }

    // DELETE
    public static void delete_product(int id) throws Exception{
        String sql = "DELETE FROM production WHERE id = ?";
         Connection con=null;
         PreparedStatement preparedStatement=null;
        try {
            con =ConnexionPgsql.dbConnect();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
         finally{
            con.close();
            preparedStatement.close();
        }
    }
    

    public static Production getProductbyId(int id)throws Exception {
        List<Production> produits = Production.get_allProducts();
        for (Production produit : produits) {
            if (produit.get_id() == id) {
                return produit;
            }
        }
        return null;
    }

    // UPDATE
    public void update_product() throws Exception{
        String sql = "UPDATE production SET id_produit = ?, date_production = ?, quantite = ? WHERE id = ?";
         Connection con=null;
         PreparedStatement preparedStatement=null;
        try {
            con =ConnexionPgsql.dbConnect();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, this.id_produit);
            preparedStatement.setDate(2, new java.sql.Date(this.date_production.getTime()));
            preparedStatement.setInt(3, this.quantite);
            preparedStatement.setInt(4, this.id);

            int rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();
            throw e;
            
        }
         finally{
            con.close();
            preparedStatement.close();
        }
    }

    // GET ALL
    public static List<Production> get_allProducts()throws Exception {
        List<Production> liste_production = new ArrayList<>();
        String sql = "SELECT * FROM view_production ORDER BY date_production";

        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs =null;
        try {
            con =ConnexionPgsql.dbConnect();
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String libelle = rs.getString("libelle");
                Date date_production = rs.getDate("date_production");
                int quantite = rs.getInt("quantite");

                Production production = new Production(id,libelle, date_production, quantite);
                liste_production.add(production);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
         finally{
            con.close();
            preparedStatement.close();
            rs.close();
        }

        return liste_production;
    }


    public static List<Production> get_SumProductsWithDate(Date date_min, Date date_max) throws Exception{
        List<Production> liste_production = new ArrayList<>();
        String sql = "SELECT * FROM view_sum_quantite_production_with_date WHERE date_production BETWEEN ? AND ?";
        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs =null;
        try {
            con=ConnexionPgsql.dbConnect();
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setDate(1, date_min);
            preparedStatement.setDate(2, date_max);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String libelle = rs.getString("libelle");
                Date date_production = rs.getDate("date_production");
                int quantite = rs.getInt("quantite");

                Production production = new Production(id, libelle, date_production, quantite);
                liste_production.add(production);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
         finally{
            con.close();
            preparedStatement.close();
            rs.close();
        }

        return liste_production;
    }

}

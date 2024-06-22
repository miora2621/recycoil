package vente;

import baseUtil.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Stock_produit {
    private String nom_produit;
    private int quantite;
    private String id_produit;

    public Stock_produit() {
    }

    public Stock_produit(String nom_produit, int quantite) {
        this.nom_produit = nom_produit;
        this.quantite = quantite;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String get_id_produit(){
        return  this.id_produit;
    }

    public void set_id_produit(String id){
        this.id_produit = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public static List<Stock_produit> all_stock() throws Exception {
        List<Stock_produit> answer = new ArrayList<>();
        String sql = "select * from stock_disponible";
        try (Connection connect = ConnexionPgsql.dbConnect();
             PreparedStatement statement = connect.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
    
            while (resultSet.next()) {
                Stock_produit stock = new Stock_produit(resultSet.getString("libelle"), resultSet.getInt("stock_disponible"));
                stock.set_id_produit(resultSet.getString("id_produit"));
                answer.add(stock);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    
        return answer;
    }

    public static int check_stock_produit(String id_produit, int isany) throws  Exception{
        int answer = 0;
        List<Stock_produit> valiny = Stock_produit.all_stock();
        for (int i = 0; i < valiny.size(); i++) {
            Stock_produit avoka = valiny.get(i);
            if(avoka.get_id_produit().equals(id_produit)){
                answer = ((int)avoka.getQuantite()) - isany;
            }
        }
        return answer;
    }
    
    public static void main(String[] args) {
        try {
           System.out.println(Stock_produit.check_stock_produit("1", 50));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

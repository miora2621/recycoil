package vente;

import utils.AssistantDB;
import utils.DateUtil;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.sql.*;

import baseUtil.*;
import java.time.Month;
import need.Benefice;

public class Vente {
    String libelle_produit;
    String viscosite;
    Long quantite;
    Double somme;

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public String getLibelle_produit() {
        return libelle_produit;
    }

    public void setLibelle_produit(String libelle_produit) {
        this.libelle_produit = libelle_produit;
    }

    public String getViscosite() {
        return viscosite;
    }

    public void setViscosite(String viscosite) {
        this.viscosite = viscosite;
    }

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
    }

    public Vente(String libelle_produit, String viscosite, Long quantite, Double somme) {
        this.libelle_produit = libelle_produit;
        this.viscosite = viscosite;
        this.quantite = quantite;
        this.somme = somme;
    }

    public Vente() {
    }

    public static void insert_vente(String id_produit, Integer quantite, Date date_vente, BigDecimal prix_unitaire, Double total) throws Exception {
        
        if (Stock_produit.check_stock_produit(id_produit,quantite.intValue()) <= 0)
        {
            throw new Exception("Stock insuffisant");
        }
        String request="INSERT INTO vente (id_produit,quantite,date_vente,prix_unitaire,total) VALUES("+
                    id_produit +","+quantite+",'"+DateUtil.formate_date(date_vente)+"',"+prix_unitaire+","+total
                +")";
        AssistantDB assistantDB = new AssistantDB();
        assistantDB.update(request);
        assistantDB.getConnection().close();
    }
    public static List list_vente(Date debut, Date fin) throws Exception {
        AssistantDB assistantDB = new AssistantDB();
        String request_view = "CREATE OR REPLACE view v_vente AS SELECT p.libelle,mp.libelle as viscosite,SUM(vente.quantite) as quantite,SUM(vente.total) as total FROM vente RIGHT JOIN produit p on vente.id_produit = p.id_produit\n" +
                "JOIN matiere_premiere mp on p.id_matiere_premiere = mp.id_matiere_premiere WHERE date_vente BETWEEN '"+ DateUtil.formate_date(debut)+"' AND '"+ DateUtil.formate_date(fin)+"' GROUP BY p.libelle, mp.libelle;";
        assistantDB.update(request_view);
        String request_data = "SELECT produit.libelle,mp.libelle as viscosite,0 as quantite, 0 as total FROM produit\n" +
                "                JOIN matiere_premiere mp on produit.id_matiere_premiere = mp.id_matiere_premiere\n" +
                "                        WHERE produit.libelle NOT IN(SELECT libelle FROM v_vente)\n" +
                "UNION ALL SELECT * FROM v_vente;";
        List toReturn = assistantDB.getData(request_data,new Vente());
        assistantDB.getConnection().close();
        return toReturn;
    }

    public static Integer last_index_vente() throws Exception
    {
        AssistantDB assistantDB = new AssistantDB();
        String request = "SELECT MAX(id_vente) FROM vente";
        Integer toReturn  = (Integer) assistantDB.getDonnees(request)[0][0];
        assistantDB.getConnection().close();
        return toReturn;
    }

    public static void create_facture(Integer id_vente,String id_produit,String date_vente,Integer quantite,BigDecimal prix_actuel,Double total) throws Exception {
        String libelle_product = Produit.nom_produit_id(id_produit);
        FileWriter file = new FileWriter("/Users/randriamalalavalisoa/facture-recycoil/vente"+id_vente.toString()+".txt");
        String toWrite = "id Vente: "+id_vente+"\n" +
                "Produit : "+libelle_product+"\n"+
                "date vente : " +date_vente+"\n"+
                "quantite : "+quantite+"\n"+
                "prix unitaire : "+prix_actuel+"\n"+
                "tota =>" +total
                ;
        file.write(toWrite);
        file.close();
    }

    public static List get_vente_deux_mois(int annee_min,int annee_max,int mois_min , int mois_max) throws SQLException{
        List total_vente = new Vector();
        String request = "select * from v_total_vente_du_mois where annee >= "+annee_min+" and annee <= "+annee_max+"";
        try (Connection connect = ConnexionPgsql.dbConnect();
            PreparedStatement statement = connect.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {           
                int year = resultSet.getInt("annee");
                int month = resultSet.getInt("mois");
                if (annee_max != annee_min) {
                    if (year == annee_min && month >= mois_min ) {
                        List atsofoka = new Vector();
                        atsofoka.add(resultSet.getInt("annee"));
                        atsofoka.add(resultSet.getInt("mois"));
                        atsofoka.add(resultSet.getDouble("total_vente"));
                        total_vente.add(atsofoka);
                        }
                    else if (year == annee_max && month <= mois_max) {
                        List atsofoka = new Vector();
                        atsofoka.add(resultSet.getInt("annee"));
                        atsofoka.add(resultSet.getInt("mois"));
                        atsofoka.add(resultSet.getDouble("total_vente"));
                        total_vente.add(atsofoka);
                    }
                }
                else{
                    if (month >= mois_min && month <= mois_max) {
                        List atsofoka = new Vector();
                        atsofoka.add(resultSet.getInt("annee"));
                        atsofoka.add(resultSet.getInt("mois"));
                        atsofoka.add(resultSet.getDouble("total_vente"));
                        total_vente.add(atsofoka);
                    }
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return  total_vente;
    }

    public static void main(String[] args) {
        try {
            List vente = Vente.get_vente_deux_mois(2024, 2024, 3, 6);
            for (int i = 0; i< vente.size();i++) {
                List avoka =(List) vente.get(i);
                System.out.println("annee :" + avoka.get(0));
                System.out.println("mois :" + avoka.get(1));
                System.out.println("total_depense :" + avoka.get(2));
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}

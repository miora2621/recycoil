package vente;

import utils.AssistantDB;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class Produit {
    Integer idProduit;
    String viscosite;
    String libelle;

    BigDecimal prix_unitaire;

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(BigDecimal prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public String getViscosite() {
        return viscosite;
    }

    public void setViscosite(String viscosite) {
        this.viscosite = viscosite;
    }

    public Produit(Integer idProduit, String viscosite, String libelle, BigDecimal prix_unitaire) {
        this.idProduit = idProduit;
        this.viscosite = viscosite;
        this.libelle = libelle;
        this.prix_unitaire = prix_unitaire;
    }

    public Produit() {
    }
    public static String nom_produit_id(String id_produit) throws SQLException {
        AssistantDB assistantDB = new AssistantDB();
        String requete =  "SELECT mp.libelle,produit.libelle FROM produit JOIN matiere_premiere mp on produit.id_matiere_premiere = mp.id_matiere_premiere WHERE produit.id_produit="+id_produit;
        Object[][] bigData = assistantDB.getDonnees(requete);
        String libelle_of_product = (String) bigData[0][1];
        assistantDB.getConnection().close();
        return libelle_of_product;
    }
    public static List listeProduit()
    {
        AssistantDB assistantDB = new AssistantDB();
        String request = "SELECT produit.id_produit,mp.libelle,produit.libelle,prix_unitaire FROM produit\n" +
                "    JOIN matiere_premiere mp on produit.id_matiere_premiere = mp.id_matiere_premiere";
        List toReturn = assistantDB.getData(request,new Produit());
        try {
            assistantDB.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }

    public static BigDecimal prix_actuel(String id_produit)
    {
        AssistantDB assistantDB = new AssistantDB();
        String request = "SELECT produit.id_produit,mp.libelle,produit.libelle,prix_unitaire FROM produit\n" +
                "    JOIN matiere_premiere mp on produit.id_matiere_premiere = mp.id_matiere_premiere WHERE produit.id_produit="+id_produit;
        List data = assistantDB.getData(request,new Produit());
        try {
            assistantDB.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ((Produit)data.get(0)).getPrix_unitaire();
    }
}

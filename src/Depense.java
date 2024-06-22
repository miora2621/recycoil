/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package need;

import baseUtil.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import  java.util.Map;

/**
 *
 * @author isaia
 */
public class Depense {
     int id;
    String raison;
    double montant;

    Date dateDepense;

    // Constructor
    public Depense(int id, String raison, double montant, Date dateDepense) {
        this.id = id;
        this.raison = raison;
        this.montant = montant;
        this.dateDepense = dateDepense;
    }

    public Depense(String raison, double montant, Date dateDepense) {
        this.raison = raison;
        this.montant = montant;
        this.dateDepense = dateDepense;
    }

    public Depense(int id) {
        this.id = id;
    }

    public Depense() {
    }
    

    // Getters
    public int getId() {
        return id;
    }

    public String getRaison() {
        return raison;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }

    // CRUD Operations

    // Create a new depense
    public void insert(Connection conn) throws SQLException {
        String query = "INSERT INTO depense (raison, montant, date_depense) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, this.getRaison());
            pstmt.setDouble(2, this.getMontant());
            pstmt.setDate(3, this.getDateDepense());
            pstmt.executeUpdate();
        }
    }

    // Read a depense by ID
    public static Depense readDepense(Connection conn, int idDepense) throws SQLException {
        String query = "SELECT id_depense, raison, montant, date_depense FROM depense WHERE id_depense = ?";
        Depense depense = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idDepense);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_depense");
                    String raison = rs.getString("raison");
                    double montant = rs.getDouble("montant");
                    Date dateDepense = rs.getDate("date_depense");
                    depense = new Depense(id, raison, montant, dateDepense);
                }
            }
        }
        return depense;
    }

    // Update a depense by ID
    public static void updateDepense(Connection conn, int idDepense, String raison, double montant, Date dateDepense) throws SQLException {
        String query = "UPDATE depense SET raison = ?, montant = ?, date_depense = ? WHERE id_depense = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, raison);
            pstmt.setDouble(2, montant);
            pstmt.setDate(3, dateDepense);
            pstmt.setInt(4, idDepense);
            pstmt.executeUpdate();
        }
    }
    

    // Delete a depense by ID
    public static void deleteDepense(Connection conn, int idDepense) throws SQLException {
        String query = "DELETE FROM depense WHERE id_depense = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idDepense);
            pstmt.executeUpdate();
        }
    }

    //getAll
    // Get all depenses
    public static List<Depense> getAll(Connection conn) throws SQLException {
        String query = "SELECT id_depense, raison, montant, date_depense FROM depense";
        List<Depense> depenses = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_depense");
                String raison = rs.getString("raison");
                double montant = rs.getDouble("montant");
                Date dateDepense = rs.getDate("date_depense");
                Depense depense = new Depense(id, raison, montant, dateDepense);
                depenses.add(depense);
            }
        }
        return depenses;
    }

    public static List<Map<String, Object>> getMonthlyExpenseTotals(Connection conn) throws SQLException {
        String query = "SELECT annee, mois, total_depense FROM v_total_depense_du_mois";
        List<Map<String, Object>> totals = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("annee", rs.getInt("annee"));
                row.put("mois", rs.getInt("mois"));
                row.put("total_depense", rs.getDouble("total_depense"));
                totals.add(row);
            }
        }
        return totals;
    }
    
        public static List<Depense> findByDate(Connection conn, Date date1, Date date2) throws SQLException, Exception {
        String query = "SELECT id_depense, raison, montant, date_depense FROM depense WHERE date_depense BETWEEN ? AND ? ";
        List<Depense> depenses = new ArrayList<>();
        try  {
            PreparedStatement pstmt = conn.prepareStatement(query);
             pstmt.setDate(1, date1);
              pstmt.setDate(2, date2);
              
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_depense");
                String raison = rs.getString("raison");
                double montant = rs.getDouble("montant");
                Date dateDepense = rs.getDate("date_depense");
                Depense depense = new Depense(id, raison, montant, dateDepense);
                depenses.add(depense);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return depenses;
    }
    
        
       public static double get_sum_by_date(Connection conn, Date date1, Date date2) throws SQLException, Exception {
        String query = "SELECT  sum(montant) AS montant FROM depense WHERE date_depense BETWEEN ? AND ? ";
        double depenses = 456;
        try  {
            PreparedStatement pstmt = conn.prepareStatement(query);
             pstmt.setDate(1, date1);
              pstmt.setDate(2, date2);
              
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                depenses = rs.getDouble("montant");
                
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return depenses;
    }

    public  static List get_entre_deux_mois(int annee_debut ,int  mois_debut,int annee_fin, int mois_fin){
        List valiny = new ArrayList<>();
        String query = "SELECT * FROM v_total_depense_du_mois WHERE annee >= ? AND annee <= ? ";
        try  {
            PreparedStatement pstmt = (ConnexionPgsql.dbConnect()).prepareStatement(query);
             pstmt.setInt(1, annee_debut);
              pstmt.setInt(2, annee_fin);
              
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int annee = rs.getInt("annee");
                int mois = rs.getInt("mois");
                if (annee_debut == annee_fin) {
                    if (mois >= mois_debut && mois <= mois_fin) {
                        if (annee == annee_debut && mois >= mois_debut) {
                            List atsofoka = new ArrayList();
                            atsofoka.add(annee);
                            atsofoka.add(mois);
                            atsofoka.add(rs.getDouble("total_depense"));
                            valiny.add(atsofoka);
                        }
                    }
                }
                else{
                    if (annee == annee_debut && mois >= mois_debut) {
                        List atsofoka = new ArrayList();
                        atsofoka.add(annee);
                        atsofoka.add(mois);
                        atsofoka.add(rs.getDouble("total_depense"));
                        valiny.add(atsofoka);
                    }
                    else if (annee == annee_fin && mois <= mois_fin) {
                        List atsofoka = new ArrayList();
                        atsofoka.add(annee);
                        atsofoka.add(mois);
                        atsofoka.add(rs.getDouble("total_depense"));
                        valiny.add(atsofoka);
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return valiny;
    }
   
}

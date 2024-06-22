/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package need;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author isaia
 */
public class Benefice {
    private int annee;
    private int mois;
    private double benefice;

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }

    public Benefice(){}

    public Benefice(int annee, int mois, double benefice){
        this.setAnnee(annee);
        this.setMois(mois);
        this.setBenefice(benefice);
    }

     //calcul des depenses dans une date donnée
    public static double getTotalDepenseDuMois(Date date, Connection conn) {
        double totalDepense = 0.0;

        // Extract year and month from the provided date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int annee = calendar.get(Calendar.YEAR);
        int mois = calendar.get(Calendar.MONTH) + 1; // Note: Month is 0-based in Calendar

        String query = "SELECT total_depense FROM v_total_depense_du_mois WHERE annee = ? AND mois = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query); {

                pstmt.setInt(1, annee);
                pstmt.setInt(2, mois);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    totalDepense = rs.getDouble("total_depense");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalDepense;
    }

    // recuperer la somme des ventes dans une date
    public static double getTotalVenteDuMois(Date date, Connection conn) throws SQLException {
        double totalVente = 0.0;

        // Extract year and month from the provided date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int annee = calendar.get(Calendar.YEAR);
        int mois = calendar.get(Calendar.MONTH) + 1; // Note: Month is 0-based in Calendar

        String query = "SELECT total_vente FROM v_total_vente_du_mois WHERE annee = ? AND mois = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, annee);
            pstmt.setInt(2, mois);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalVente = rs.getDouble("total_vente");
                }
            }
        }
        return totalVente;
    }

    // recuperer les benefices a une date donnée
    public static double getBenefice(Date date, Connection conn) throws SQLException {
        double benefice = 0.0;

        // Extraire l'année et le mois de la date fournie
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int annee = calendar.get(Calendar.YEAR);
        int mois = calendar.get(Calendar.MONTH) + 1; // Note: Month is 0-based in Calendar

        String query = "SELECT benefice FROM v_benefice WHERE annee = ? AND mois = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, annee);
            pstmt.setInt(2, mois);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    benefice = rs.getDouble("benefice");
                }
            }
        }

        return benefice;
    }
    // recuperer les benefices a une date donnée
    public static List<Benefice> getBenefices(int anneeMin, int anneeMax, int moisMin, int moisMax, Connection conn) throws SQLException {
        List<Benefice> benefices = new Vector<>();
        String query = "SELECT * FROM v_benefice WHERE annee >= ? AND annee <= ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, anneeMin);
            pstmt.setInt(2, anneeMax);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int year = rs.getInt("annee");
                    int month = rs.getInt("mois");
                    if (year == anneeMin && month >= moisMin ) {
                        benefices.add(new Benefice(rs.getInt("annee"), rs.getInt("mois"), rs.getDouble("benefice")));
                    }
                    else if (year == anneeMax && month <= moisMax) {
                        benefices.add(new Benefice(rs.getInt("annee"), rs.getInt("mois"), rs.getDouble("benefice")));
                    }
                }
            }
        }

        return benefices;
    }
    
    public static double get_vente_exemple() {
        return 300000.00;
    }
    
    public static double calculerBenefice(Date date, Connection conn) {
        double totalDepense = getTotalDepenseDuMois(date, conn);
        double totalVentes = get_vente_exemple();
        double benefice = totalVentes - totalDepense;
        return benefice;
    }

}

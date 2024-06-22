package absence;

import java.sql.*;
import baseUtil.*;
import java.util.Vector;

public class AbsenceNonJustifie{
    int idPersonnel;
    String nom;
    String poste;
    Date date;


    public static int getNbAbsence(Date dateEarlier,Date dateLater)throws Exception{
            return AbsenceNonJustifie.getAbsence(dateEarlier,dateLater,"","").size();
    }

    
    public static Vector<AbsenceNonJustifie> getAbsence(Date dateEarlier,Date dateLater,String name,String poste)throws Exception{
         Vector<AbsenceNonJustifie> ans=new Vector<AbsenceNonJustifie>(); 
         Connection connect=ConnexionPgsql.dbConnect();
         if(dateEarlier.compareTo(dateLater)>0){
            throw new Exception("la  premiere date doit etre plus recente ");
         }
         int counter=0;
         while(dateEarlier.compareTo(dateLater)<=0)
          {
                ans.addAll(AbsenceNonJustifie.getAbsence(dateEarlier,name,poste,connect));
                dateEarlier.setDate(dateEarlier.getDate()+1);
                
          }  
          connect.close();
          return ans ;
    }
    public static Vector<AbsenceNonJustifie> getAbsence(Date date,String name,String poste,Connection connect )throws Exception
    {
         String sql="select * from   v_absence_journaliere where nom like '%"+name+"%'  and poste like '%"+poste+"%'";
        AbsenceNonJustifie.updatePresent(date,connect);
        PreparedStatement statement=null;
        ResultSet resultSet = null;
        Vector<AbsenceNonJustifie> ans=new Vector<AbsenceNonJustifie>();
        try{
            statement = connect.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                AbsenceNonJustifie temp=new AbsenceNonJustifie(resultSet.getInt("id_Personnel"),resultSet.getString("nom"),resultSet.getString("poste"),new Date(date.getTime()));
                ans.add(temp);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        finally{
            statement.close();
            resultSet.close();
        }
        return ans ;
    }
    public static void updatePresent(Date date,Connection connect)throws Exception
    {
        String sql = "CREATE OR REPLACE VIEW v_presence_journaliere AS " +
                "SELECT personnel.id_Personnel " +
                "FROM personnel " +
                "JOIN presence ON personnel.id_Personnel = presence.id_Personnel " +
                "WHERE Date_Presence = '"+date+"' AND personnel.date_embauche < '"+date+"' " +
                "UNION ALL " +
                "SELECT personnel.id_Personnel " +
                "FROM personnel " +
                "JOIN Absence ON personnel.id_Personnel = Absence.id_Personnel " +
                "WHERE Date_Absence = '"+date+"' AND personnel.date_embauche < '"+date+"' "+
                "UNION ALL "+
                "select id_personnel from v_conge where date_debut_conge >= '"+date+"' and date_fin_conge <= '"+date+"';";
        PreparedStatement statement = null;
        try{

            statement = connect.prepareStatement(sql);

            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        finally{
            statement.close();
        }
    }

    public AbsenceNonJustifie(int idPersonnel, String nom, String poste, Date date) {
        this.idPersonnel = idPersonnel;
        this.nom = nom;
        this.poste = poste;
        this.date = date;
    }

    public int getIdPersonnel() {
        return idPersonnel;
    }

    public String getNom() {
        return nom;
    }

    public String getPoste() {
        return poste;
    }

    public Date getDate() {
        return date;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
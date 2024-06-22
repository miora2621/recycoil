package Personnel;

import utils.AssistantDB;
import utils.DateUtil;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Retard {
    Integer idPersonnel;
    String nom;
    String poste;
    Time heureDebut;

    Date datePresence;

    public Date getDatePresence() {
        return datePresence;
    }

    public void setDatePresence(Date datePresence) {
        this.datePresence = datePresence;
    }


    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Retard(Integer idPersonnel, String nom, String poste, Time heureDebut,Date datePresence) {
        this.idPersonnel = idPersonnel;
        this.nom = nom;
        this.poste = poste;
        this.heureDebut = heureDebut;
        this.datePresence=datePresence;
    }

    public Retard() {
    }
    public List<Retard> retards_between_date(Date debut, Date fin) throws Exception {
        AssistantDB assistantDB = new AssistantDB();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(debut);
        List<Retard> toReturn = new ArrayList<>();
        while (!calendar.getTime().after(fin)) {
            String currentDate = DateUtil.get_string_date(calendar.getTime());
            String request =
                    "        SELECT personnel.id_Personnel,personnel.nom, P.poste,Heure_Debut,Date_Presence FROM personnel\n" +
                    "            JOIN Poste P on personnel.id_Poste = P.id_Poste JOIN presence ON personnel.id_Personnel = presence.id_Personnel\n" +
                    "        WHERE Date_Presence = '"+currentDate+"' AND Heure_Debut>'08:00:00';";

            List temporaire_list =  assistantDB.getData(request,new Retard());
            for (Object retard : temporaire_list){
                toReturn.add((Retard) retard);
            }
            calendar.add(Calendar.DATE, 1);
        }
        assistantDB.getConnection().close();
        return toReturn;
    }
    public boolean check_idPersonnel_exist(String idPersonnel)
    {
        AssistantDB assistantDB = new AssistantDB();
        Object[][] liste  = assistantDB.getDonnees("SELECT * FROM personnel WHERE id_Personnel="+idPersonnel);
        try {
            assistantDB.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (liste.length<1 || liste==null) {
            return false;
        }
        return true;
    }

    public boolean check_if_present(String idPersonnel,Date datePresence){
        AssistantDB assistantDB = new AssistantDB();
        String currentDate = DateUtil.get_string_date(datePresence);
        Object[][] liste  = assistantDB.getDonnees("SELECT * FROM presence WHERE id_Personnel="+idPersonnel+" AND Date_Presence='"+currentDate+"'");
        try {
            assistantDB.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (liste.length<1 || liste==null) return false;
        return true;
    }

    public boolean check_if_out(String idPersonnel,Date datePresence){
        AssistantDB assistantDB = new AssistantDB();
        String currentDate = DateUtil.get_string_date(datePresence);
        Object[][] liste  = assistantDB.getDonnees("SELECT * FROM presence WHERE id_Personnel="+idPersonnel+" AND Date_Presence='"+currentDate+"' AND Heure_Fin IS NOT NULL");
        try {
            assistantDB.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (liste.length<1 || liste==null) return false;
        return true;
    }
    public void insert_entry(Date datePresence,Time heureDebut,String idPersonnel) throws Exception {
        AssistantDB assistantDB = new AssistantDB();
        if (check_idPersonnel_exist(idPersonnel)==false)
        {
            throw new Exception("Personnel non existant");
        }
        if (check_if_present(idPersonnel,datePresence)==true)
        {
            throw new Exception("Personnel dejà present");
        }
        String currentDate = DateUtil.get_string_date(datePresence);
        String request =  "INSERT INTO presence(Date_Presence,Heure_Debut,Heure_Fin,id_Personnel) VALUES('"+currentDate+"' , '"+heureDebut+"' ,NULL ,"+idPersonnel+")";
        assistantDB.update(request);
        assistantDB.getConnection().close();
    }

    public void insert_out(Date datePresence,Time heureFin,String idPersonnel) throws Exception {
        AssistantDB assistantDB = new AssistantDB();
        String currentDate = DateUtil.get_string_date(datePresence);
        Object[][] liste  = assistantDB.getDonnees("SELECT Heure_Debut FROM presence WHERE id_Personnel="+idPersonnel+" AND Date_Presence='"+currentDate+"'");
        assistantDB.getConnection().close();
        if (liste.length<1 || liste==null)  throw new Exception("Personnel non present");

        if (check_if_out(idPersonnel,datePresence) == true) throw new Exception("Personnel deja sorti");


        Time myDebut =(Time)liste[0][0];
        if (myDebut.after(heureFin)) throw new Exception("Heure de sortie invalide");
        assistantDB = new AssistantDB();
        assistantDB.update("UPDATE presence SET heure_fin = '"+heureFin.toString()+"' WHERE id_Personnel="+idPersonnel+" AND Date_Presence ='"+currentDate+"'");
        assistantDB.getConnection().close();
    }
}

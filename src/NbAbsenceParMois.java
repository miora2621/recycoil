package absence;

import java.sql.Date;
import java.util.Vector;

public class NbAbsenceParMois{
    int nbAbsents;
    int mois;
    int annee;

    public static  String getNbAbsParMoisJSON(Date dateTot ,Date dateTard)throws Exception{
        Vector<NbAbsenceParMois> lsNbAb=NbAbsenceParMois.getNbAbsParMois(dateTot,dateTard);
        String ans="[";
        for(int i=0;i<lsNbAb.size();i++){
            ans=ans+"{nbAbsents:"+lsNbAb.elementAt(i).getNbAbsents()+",";
            ans=ans+"mois:"+lsNbAb.elementAt(i).getMois()+",";
            ans=ans+"annee:"+lsNbAb.elementAt(i).getAnnee()+"}";
            if(i!=lsNbAb.size()-1){
                ans=ans+",";
            }
        }
        ans=ans+"]";
        return ans;  
    } 

    public static Vector<NbAbsenceParMois> getNbAbsParMois(Date dateTot ,Date dateTard )throws Exception{
         if(dateTot.compareTo(dateTard)>0){
            throw new Exception("la  premiere date doit etre plus recente ");
         }
         Vector<NbAbsenceParMois> lsNbAb=new Vector<NbAbsenceParMois>();
          int counter=0;
         while(dateTot.compareTo(dateTard)<=0)
          {
                lsNbAb.add(new NbAbsenceParMois(AbsenceNonJustifie.getNbAbsence(dateTot,dateTard),dateTot.getMonth(),dateTot.getYear()));
                dateTot.setMonth(dateTot.getMonth()+1);
                
          }  
        return lsNbAb;
    }
     // Constructeur
    public NbAbsenceParMois(int nbAbsents, int mois, int annee) {
        this.nbAbsents = nbAbsents;
        this.mois = mois;
        this.annee = annee;
    }

    // Setters
    public void setNbAbsents(int nbAbsents) {
        this.nbAbsents = nbAbsents;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    // Getters
    public int getNbAbsents() {
        return nbAbsents;
    }

    public int getMois() {
        return mois;
    }

    public int getAnnee() {
        return annee;
    }
}
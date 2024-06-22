package Personnel;

import utils.AssistantDB;

import java.sql.SQLException;
import java.util.List;

public class MyPoste {
    Integer idPoste;
    String poste;


    public MyPoste() {

    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public MyPoste(Integer idPoste, String poste) {
        this.idPoste = idPoste;
        this.poste = poste;
    }

    public List listePoste() {
        AssistantDB assistantDB = new AssistantDB();
        List toReturn = assistantDB.getData("SELECT * FROM poste",new MyPoste());
        try {
            assistantDB.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }
}

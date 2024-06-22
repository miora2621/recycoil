package Personnel;

import com.google.gson.Gson;
import utils.AssistantDB;

import java.sql.SQLException;
import java.util.List;

public class MyPersonnel {
    Integer idPersonnel;
    String nom;

    String post;


    public MyPersonnel(Integer idPersonnel, String nom, String post) {
        this.idPersonnel = idPersonnel;
        this.nom = nom;
        this.post = post;
    }

    public MyPersonnel() {

    }
    public List getPersonnelByPost(String idPoste)
    {
        AssistantDB assistantDB = new AssistantDB();
        String request = " SELECT personnel.id_Personnel , personnel.nom, P.poste FROM personnel\n" +
                "    JOIN Poste P on personnel.id_Poste = P.id_Poste WHERE personnel.id_Poste = "+idPoste;
        List toReturn = assistantDB.getData(request,new MyPersonnel());
        try {
            assistantDB.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }

    public String data_json(String idPoste)
    {
        List<MyPersonnel> list = getPersonnelByPost(idPoste);
        try {
            String json = new Gson().toJson(list);
            System.out.println(json);
            return json;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}

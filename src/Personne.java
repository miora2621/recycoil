package front_office;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import baseUtil.ConnexionPgsql;

public class Personne {
    int id_personne;
    int id_personnel;
    String email;
    String mot_de_passe;
    String etat;

    public int get_id_personne() { return this.id_personne; }
    public void set_id_personne(int id_personne) { this.id_personne = id_personne; }

    public int get_id_personnel() { return this.id_personnel; }
    public void set_id_personnel(int id_personnel) { this.id_personnel = id_personnel; }

    public String get_email() { return this.email; }
    public void set_email(String email) { this.email = email; }

    public String get_mot_de_passe() { return this.mot_de_passe; }
    public void set_mot_de_passe(String mot_de_passe) { this.mot_de_passe = mot_de_passe; }

    public String get_etat() { return this.etat; }
    public void set_etat(String etat) { this.etat = etat; }

    public Personne() {}
    public Personne(int id_personne, String email, String mot_de_passe, String etat) {
        this.set_id_personne(id_personne);
        this.set_email(email);
        this.set_mot_de_passe(mot_de_passe);
        this.set_etat(etat);
    }

    public static Personne login(String email, String mot_de_passe) {
        Personne personne = new Personne(0, email, mot_de_passe, null);
        List<Personne> ls_persons = personne.get(true);

        if (ls_persons.size() == 1) {
            return (Personne) ls_persons.get(0);
        } else {
            return personne;
        }
    }


    //// CRUD
    public void insert() {
        Connection connection = null;
        PreparedStatement statement = null; 

        try {
            // Préparer la requête SQL pour l'insertion
            connection = ConnexionPgsql.dbConnect();
            String query = "INSERT INTO personne(id_personnel, email, mot_de_passe, etat) VALUES (?, ?, ?, ?)";
            System.out.println("Query: " + query);

            // Définir les valeurs des paramètres de la requête
            statement = connection.prepareStatement(query);
            statement.setInt(1, this.get_id_personnel());
            statement.setString(2, this.get_email());
            statement.setString(3, this.get_mot_de_passe());
            statement.setString(4, this.get_etat());

            // Exécuter la requête d'insertion
            statement.executeUpdate();

            System.out.println("Personne ajoutée avec succès !");

        } catch (Exception e) {
            System.out.println("An error has occurred : " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Fermer les ressources JDBC
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update() {
        Connection connection = null;
        PreparedStatement statement = null; 

        try {
            // Préparer la requête SQL pour l'insertion
            connection = ConnexionPgsql.dbConnect();
            String query = "UPDATE personne SET id_personnel = ?, email = ?, mot_de_passe = ?, etat = ? WHERE id_personne = ?";
            System.out.println("Query: " + query);

            // Définir les valeurs des paramètres de la requête
            statement = connection.prepareStatement(query);
            statement.setInt(1, this.get_id_personnel());
            statement.setString(2, this.get_email());
            statement.setString(3, this.get_mot_de_passe());
            statement.setString(4, this.get_etat());

            // Exécuter la requête d'insertion
            statement.executeUpdate();

            System.out.println("Personne modifiee avec succès !");

        } catch (Exception e) {
            System.out.println("An error has occurred : " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Fermer les ressources JDBC
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete() {
        Connection connection = null;
        PreparedStatement statement = null; 

        try {
            // Préparer la requête SQL pour l'insertion
            connection = ConnexionPgsql.dbConnect();
            String query = "DELETE FROM personne WHERE id_personne = ?";
            System.out.println("Query: " + query);

            // Définir les valeurs des paramètres de la requête
            statement = connection.prepareStatement(query);
            statement.setInt(1, this.get_id_personne());

            // Exécuter la requête d'insertion
            statement.executeUpdate();

            System.out.println("Personne supprimee avec succès !");

        } catch (Exception e) {
            System.out.println("An error has occurred : " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Fermer les ressources JDBC
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Personne> get(boolean string_precision) {
        List<Personne> ls_personne = new ArrayList<Personne>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnexionPgsql.dbConnect();

            String query = "SELECT * FROM personne WHERE 1=1";
            // Verification des valeurs de l'attribut de l'appelant
            if (this.get_id_personne() != 0) {
                query += " AND id_personne = ?";
            }
            if (this.get_id_personnel() != 0) {
                query += " AND id_personnel = ?";
            }
            if (this.get_email() != null) {
                query += " AND email = ?";
            }
            if (this.get_mot_de_passe() != null) {
                query += " AND mot_de_passe = ?";
            }
            if (this.get_etat() != null) {
                query += " AND etat =?";
            }
            System.out.println("Query: " + query);

            // Definir les valeurs des parametres de la requete (s'il y en a)
            statement = connection.prepareStatement(query);
            int indice = 1;
            if (this.get_id_personne() != 0) {
                statement.setInt(indice, this.get_id_personne());
                indice++;
            }
            if (this.get_id_personnel() != 0) {
                statement.setInt(indice, this.get_id_personnel());
                indice++;
            }
            if (this.get_email() != null) {
                statement.setString(indice, this.get_email());
                indice++;
            }
            if (this.get_mot_de_passe() != null) {
                statement.setString(indice, this.get_mot_de_passe());
                indice++;
            }
            if (this.get_etat() != null) {
                statement.setString(indice, this.get_etat());
                indice++;
            }

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Personne p = new Personne();
                p.set_id_personne(resultSet.getInt("id_personne"));
                p.set_email(resultSet.getString("email"));
                p.set_mot_de_passe(resultSet.getString("mot_de_passe"));
                p.set_etat(resultSet.getString("etat"));
                ls_personne.add(p);
            }

        } catch (Exception e) {
            System.out.println("An error has occurred : " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Fermer les ressources JDBC
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return ls_personne;
    }

}
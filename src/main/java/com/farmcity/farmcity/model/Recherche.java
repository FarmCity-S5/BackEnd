package com.farmcity.farmcity.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Recherche {
    int id_culture;
    int id_parcelle;
    int id_user;

    public int getId_culture() {
        return id_culture;
    }

    public int getId_parcelle() {
        return id_parcelle;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_culture(int id_culture) {
        this.id_culture = id_culture;
    }

    public void setId_parcelle(int id_parcelle) {
        this.id_parcelle = id_parcelle;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public Recherche(int id_culture,int id_parcelle,int id_user){
        setId_parcelle(id_culture);
        setId_parcelle(id_parcelle);
        setId_user(id_user);
    }
    public static List<Recherche> multirecherches(Connection con,int idCulture,int idParcelle,int idUser) {
        List<Recherche> recherches = new ArrayList<>();

        try {
            String sql = "select * from v_recherche\n" +
                    "where id_culture = ? and id_parcelle = ? and id_user= ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1,idCulture);
                preparedStatement.setInt(2, idParcelle);
                preparedStatement.setInt(3, idUser);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Recherche recherche = new Recherche(
                            resultSet.getInt("id_culture"),
                            resultSet.getInt("id_parcelle"),
                            resultSet.getInt("id_user")

                    );
                    recherches.add(recherche);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recherches;

    }
}


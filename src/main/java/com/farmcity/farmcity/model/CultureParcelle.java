package com.farmcity.farmcity.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CultureParcelle {
    int id_parcelle;
    int id_culture;
    Timestamp date_plantation;

    public int getId_culture() {
        return id_culture;
    }

    public int getId_parcelle() {
        return id_parcelle;
    }

    public Timestamp getDate_plantation() {
        return date_plantation;
    }

    public void setId_culture(int id_culture) {
        this.id_culture = id_culture;
    }

    public void setId_parcelle(int id_parcelle) {
        this.id_parcelle = id_parcelle;
    }

    public void setDate_plantation(Timestamp date_plantation) {
        this.date_plantation = date_plantation;
    }
    public CultureParcelle(int id_parcelle,int id_culture,Timestamp date_plantation){
        setId_parcelle(id_parcelle);
        setId_culture(id_culture);
        setDate_plantation(date_plantation);
    }
   /* public static List<CultureParcelle> statCultureParcelle(Connection con) {
        List<CultureParcelle> cultureParcelles = new ArrayList<>();

        try{
            String sql = "select P.id_terrain, C.id id_culture, C.name_culture nom_culture, count(C.id) nombre_culture\n" +
                    "from cultureparcelle\n" +
                    "join Culture C on cultureparcelle.id_culture = C.id\n" +
                    "join Parcelle P on cultureparcelle.id_parcelle = P.id\n" +
                    "\n" +
                    "group by id_terrain, C.id;";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id_terrain = resultSet.getInt("id_terrain");
                    int id_culture = resultSet.getInt("id_culture");
                    String nom_culture = resultSet.getString("nom_culture");
                    int nombre_culture = resultSet.getInt("nombre_culture");

                    CultureParcelle cultureParcelle = new CultureParcelle(id_terrain,id_culture,nom_culture,nombre_culture );
                    cultureParcelles.add(cultureParcelle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cultureParcelles;
    }*/
}

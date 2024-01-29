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

    public CultureParcelle(int id_parcelle, int id_culture, Timestamp date_plantation) {
        setId_parcelle(id_parcelle);
        setId_culture(id_culture);
        setDate_plantation(date_plantation);
    }
    public static List<CultureParcelle> getAllCulturesParcelles(Connection con) {
        List<CultureParcelle> cultureparcelles = new ArrayList<>();

        try{
            String sql = "SELECT * FROM CultureParcelle";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_parcelle = resultSet.getInt("id_parcelle");
                int id_culture = resultSet.getInt("id_culture");
                Timestamp date_plantation = resultSet.getTimestamp("date_plantation");

                CultureParcelle cultureParcelle = new CultureParcelle(id_parcelle, id_culture, date_plantation);
                cultureparcelles.add(cultureParcelle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cultureparcelles;
    }

    public  static void AddCultureParcelle(Connection con, int id_parcelle,int id_culture,Timestamp date_plantation) {
        try  {
            String sql = "INSERT INTO CultureParcelle (id_parcelle, id_culture, date_plantation) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id_parcelle);
            preparedStatement.setInt(2, id_culture);
            preparedStatement.setTimestamp(3, date_plantation);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}

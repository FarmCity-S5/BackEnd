package com.farmcity.farmcity.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueTerrain {
    int id;
    int id_vendeur;
    int id_acheteur;
    Date date_achat;

    public int getId() {
        return id;
    }

    public int getId_vendeur() {
        return id_vendeur;
    }

    public int getId_acheteur() {
        return id_acheteur;
    }

    public Date getDate_achat() {
        return date_achat;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setId_vendeur(int id_vendeur) {
        this.id_vendeur = id_vendeur;
    }

    public void setId_acheteur(int id_acheteur) {
        this.id_acheteur = id_acheteur;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }



    public HistoriqueTerrain(int id, int id_vendeur, int id_acheteur, Date date_achat){
        setId(id);
        setId_vendeur(id_vendeur);
        setId_acheteur(id_acheteur);
        setDate_achat(date_achat);
    }
    public static List<HistoriqueTerrain> getAllHistorique(Connection con) {
        List<HistoriqueTerrain> historiqueTerrains = new ArrayList<>();

        try{
            String sql = "SELECT * FROM Historiqueterrain";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int id_vendeur = resultSet.getInt("id_vendeur");
                    int id_acheteur = resultSet.getInt("id_acheteur");
                    Date date_achat = resultSet.getDate("date_achat");

                    HistoriqueTerrain historiqueTerrain = new HistoriqueTerrain(id, id_vendeur,id_acheteur, date_achat );
                    historiqueTerrains.add(historiqueTerrain);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historiqueTerrains;
    }

    public static void addHistoriqueTerrain(Connection con, int id_vendeur,int id_acheteur,Date date_achat ) {
        try  {

            String sql = "INSERT INTO HistoriqueTerrain (id_vendeur,id_acheteur,date_achat) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id_vendeur);
            preparedStatement.setInt(2,id_acheteur);
            preparedStatement.setDate(3,date_achat);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

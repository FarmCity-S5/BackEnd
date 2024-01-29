package com.farmcity.farmcity.model;

import com.farmcity.farmcity.controller.ConnectPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Culture{
    String name_culture;
    int prix;
    Time duration;
    public String getName_culture(){

        return name_culture;
    }
    public int getPrix() {

        return prix;
    }

    public Time getDuration() {

        return duration;
    }

    public void setDuration(Time duration) {

        this.duration = duration;
    }

    public void setName_culture(String name_culture) {

        this.name_culture = name_culture;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    public Culture (String name_culture,int prix,Time duration){
        setName_culture(name_culture);
        setPrix(prix);
        setDuration(duration);
    }
    public static List<Culture> getAllCultures(Connection con) {
        List<Culture> cultures = new ArrayList<>();

        try{
            String sql = "SELECT * FROM Culture";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String nameCulture = resultSet.getString("name_culture");
                    int prix = resultSet.getInt("prix");
                    Time duration = resultSet.getTime("duration");

                    Culture culture = new Culture(nameCulture, prix, duration);
                    cultures.add(culture);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cultures;
    }

    public  static void AddCultures(Connection con, String name_culture,int prix,Time duration) {
        try  {
            String sql = "INSERT INTO Culture (name_culture, prix, duration) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name_culture);
            preparedStatement.setInt(2, prix);
            preparedStatement.setTime(3, duration);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double calculerTempsRestant(Connection connection, int idParcelle) throws SQLException {
        String query = "SELECT C.duration, CP.date_plantation FROM CultureParcelle CP " +
                "JOIN Culture C ON CP.id_culture = C.id " +
                "WHERE CP.id_parcelle = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idParcelle);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Time dureeEnMinutes = resultSet.getTime("duration");
                Timestamp datePlantation = resultSet.getTimestamp("date_plantation");
                double pourcentageRestant = calculerPourcentageRestant(timeToMinute(dureeEnMinutes), datePlantation);

                return pourcentageRestant;
            }
        }

        return -1;
    }

    public static int timeToMinute(Time duration){
        String duree = duration.toString();
        String[] durees = duree.split(":");
        return Integer.parseInt(durees[0]) * 60 + Integer.parseInt(durees[1]);
    }

    /*public static void main(String[] args) throws Exception{
        ConnectPost cp = new ConnectPost();
        Connection con = cp.ConnectionBase();
        double test = calculerTempsRestant(con, 5);
        System.out.println(test);
    }*/
    private static double calculerPourcentageRestant(int dureeEnMinute, Timestamp datePlantation) {
        long tempsEcoule = calculerTempsEcouleEnMinutes(datePlantation);
        double pourcentageRestant = (100.0 * tempsEcoule) / dureeEnMinute;
        if(pourcentageRestant > 100)
            pourcentageRestant = 100;
        // double pourcentageRestant = 100.0 - ((tempsEcouleEnMinutes / duree) * 100.0);
        return pourcentageRestant;
    }
    private static long calculerTempsEcouleEnMinutes(Timestamp datePlantation) {
        long maintenant = System.currentTimeMillis();
        long datePlantationMillis = datePlantation.getTime();
        long differenceMillis = maintenant - datePlantationMillis;
        return differenceMillis / (60 * 1000);
    }


}

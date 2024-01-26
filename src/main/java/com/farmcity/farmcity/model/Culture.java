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


}

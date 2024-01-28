package com.farmcity.farmcity.model;

import com.farmcity.farmcity.controller.ConnectPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    int id_terrain;
    float longitude;
    float latitude;
    int nombre_parcelle;
    int etat;
    String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_terrain() {
        return id_terrain;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getNombre_parcelle() {
        return nombre_parcelle;
    }

    public int getEtat() {
        return etat;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setNombre_parcelle(int nombre_parcelle) {
        this.nombre_parcelle = nombre_parcelle;
    }

    public Terrain (float longitude,float latitude,int nombre_parcelle,int etat,String description){
        setLongitude(longitude);
        setLatitude(latitude);
        setNombre_parcelle(nombre_parcelle);
        setEtat(etat);
        setDescription(description);
    }
    
    public static List<Terrain> getAllTerrains(Connection con) {
        List<Terrain> terrains = new ArrayList<>();

        try{
            String sql = "SELECT * FROM Terrain";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    float longitude = resultSet.getFloat("longitude");
                    float latitude = resultSet.getFloat("latitude");
                    Integer nombre_parcelle = resultSet.getInt("nombre_parcelle");
                    Integer etat = resultSet.getInt("etat");
                    String description = resultSet.getString("descriptionTerrain");

                    Terrain terrain = new Terrain(longitude, latitude, nombre_parcelle, etat,description);
                    terrains.add(terrain);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return terrains;
    }

    public static void addTerrain(Connection con, float longitude,float latitude,int nombre_parcelle,int etat ,String description) {
        try  {
            String sql = "INSERT INTO Terrain (longitude,latitude, nombre_parcelle, etat) VALUES (?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, longitude);
            preparedStatement.setDouble(2, latitude);
            preparedStatement.setInt(3, nombre_parcelle);
            preparedStatement.setInt(4, etat);
            preparedStatement.setString(5, description);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

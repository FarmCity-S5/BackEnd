package com.farmcity.farmcity.model;

import com.farmcity.farmcity.controller.ConnectPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Parcelle {
    int id_parcelle;
    int id_terrain;
    float rendement;
    int superficie;

    public int getId_parcelle() {
        return id_parcelle;
    }

    public int getId_terrain() {
        return id_terrain;
    }

    public float getRendement() {
        return rendement;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setId_parcelle(int id_parcelle) {
        this.id_parcelle = id_parcelle;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public void setRendement(float rendement) {
        this.rendement = rendement;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public Parcelle(int id_parcelle,int id_terrain, float rendement,int superficie){
        setId_parcelle(id_parcelle);
        setId_terrain(id_terrain);
        setRendement(rendement);
        setSuperficie(superficie);
    }

    public static List<Parcelle> getAllParcelles(Connection con) {
        List<Parcelle> parcelles = new ArrayList<>();

        try{
            String sql = "SELECT * FROM Parcelle";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery())
                 {
                while (resultSet.next()) {
                    Integer id_parcelle = resultSet.getInt("id");
                    Integer id_terrain = resultSet.getInt("id_terrain");
                    float rendement = resultSet.getFloat("rendement");
                    int superficie = resultSet.getInt("superficie");

                    Parcelle parcelle = new Parcelle(id_parcelle, id_terrain,rendement,superficie);
                    parcelles.add(parcelle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelles;
    }

    public static void AddParcelles(Connection con, int id_terrain,float rendement,int superficie) {
        try  {

            String sql = "INSERT INTO Parcelle (id_terrain, rendement, superficie) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql) ;
            preparedStatement.setInt(1, id_terrain);
            preparedStatement.setFloat(2, rendement);
            preparedStatement.setInt(3, superficie);
            preparedStatement.executeUpdate();
            }
         catch(SQLException e) {
            e.printStackTrace();
        }
    }

}

    



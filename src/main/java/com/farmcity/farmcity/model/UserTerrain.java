package com.farmcity.farmcity.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTerrain {
     int id_user;
     int id_terrain;

    public int getId_terrain() {
        return id_terrain;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public UserTerrain(int id_user, int id_terrain){
        setId_user(id_user);
        setId_terrain(id_terrain);
    }
    public static List<UserTerrain> getUserTerrains(Connection con) {
        List<UserTerrain> userTerrains = new ArrayList<>();

        try{
            String sql = "SELECT * FROM UserTerrain";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id_user = resultSet.getInt("id_user");
                    int id_terrain = resultSet.getInt("id_terrain");


                    UserTerrain userTerrain = new UserTerrain(id_user,id_terrain);
                    userTerrains.add(userTerrain);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userTerrains;
    }

    public static void addUser(Connection con,int id_user,int id_terrain) {
        try  {
            String sql = "INSERT INTO UserTerrain (id_user,id_terrain) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id_user);
            preparedStatement.setInt(2,id_terrain);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

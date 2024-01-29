package com.farmcity.farmcity.model;

import com.farmcity.farmcity.controller.ConnectPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    int id;
    String name_user;
    String pass_word;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName_user() {
        return name_user;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }
    public Utilisateur(int id, String name_user,String pass_word){
        setId(id);
        setName_user(name_user);
        setPass_word(pass_word);
    }

        public static Utilisateur login(Connection con, String name_user, String pass_word) throws Exception {
            List<Utilisateur> utilisateurs = new ArrayList<>();
            String sql = " SELECT * FROM UserFarm where name_user = ? and pass_word = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name_user);
            preparedStatement.setString(2, pass_word);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("name_user");
                int id = resultSet.getInt("id");
                String password  = resultSet.getString("pass_word");

                Utilisateur utilisateur = new Utilisateur(id, username, password);
                utilisateurs.add(utilisateur);
            }
            if(utilisateurs.size() <= 0)
                return null;
            else return utilisateurs.get(0);
        }
    public static void AddClientUser (Connection con,String name_user,String pass_word) {
        try  {
            String sql = "INSERT INTO UserFarm (name_user,pass_word) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,name_user);
            preparedStatement.setString(2,pass_word);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}


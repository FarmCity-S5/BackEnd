package com.farmcity.farmcity.model;

import com.farmcity.farmcity.controller.ConnectPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    String name_user;
    String pass_word;

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
    public Utilisateur(String name_user,String pass_word){
        setName_user(name_user);
        setPass_word(pass_word);
    }

    public List<Utilisateur> getAllUser() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try{
            ConnectPost c = new ConnectPost();
            Connection con = c.ConnectionBase();
            String sql = "SELECT * FROM UserFarm";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(); {

                while (resultSet.next()) {
                    String name_user = resultSet.getString("name_user");
                    String passWord = resultSet.getString("pass_word");

                    Utilisateur utilisateur = new Utilisateur(name_user,pass_word);
                    utilisateurs.add(utilisateur);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }


}

package com.farmcity.farmcity.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Message {
    String id;
    int id_sender;
    int id_receive;
    String message;
    Timestamp date_message;

    public String getId() {
        return id;
    }

    public int getId_sender() {
        return id_sender;
    }

    public int getId_receive() {
        return id_receive;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getDate_message() {
        return date_message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    public void setId_receive(int id_receive) {
        this.id_receive = id_receive;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate_message(Timestamp date_message) {
        this.date_message = date_message;
    }
    public Message(String id,int id_sender,int id_receive,String message,Timestamp date_message){
        setId(id);
        setId_sender(id_sender);
        setId_receive(id_receive);
        setMessage(message);
        setDate_message(date_message);
    }
    public static List<Message> getAllmessages(Connection con) {
        List<Message> messages = new ArrayList<>();

        try{
            String sql = "SELECT * FROM Message";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    int id_sender = resultSet.getInt("id_sender");
                    int id_receive = resultSet.getInt("id_receive");
                    String mes = resultSet.getString("message");
                    Timestamp date_message = resultSet.getTimestamp("date_message");

                    Message message = new Message(id, id_sender,id_receive, mes,date_message );
                    messages.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static void addMessage(Connection con, int id_sender,int id_receive,String message,Timestamp date_message ) {
        try  {

            String sql = "INSERT INTO Message   (id_sender,id_receive,message ,date_achat) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id_sender);
            preparedStatement.setInt(2,id_receive);
            preparedStatement.setString(3, message);
            preparedStatement.setTimestamp(4,date_message);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

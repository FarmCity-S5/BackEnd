package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {
    @PostMapping()
    public void save(
            @RequestParam int id_sender,
            @RequestParam int id_receive,
            @RequestParam String message,
            @RequestParam Timestamp date_message
            ) throws SQLException {
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            Message.addMessage(con, id_sender, id_receive, message,date_message);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getAllmessages()throws SQLException{
        ConnectPost cp = new ConnectPost();
        List<Message> messages= new ArrayList<>();
        try(Connection con = cp.ConnectionBase()) {
            messages = Message.getAllmessages(con);
        }
        return ResponseEntity.ok(messages);
    }
}

package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.UserTerrain;
import com.farmcity.farmcity.model.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("utilisateur")
public class UtilisateurController {
    @PostMapping
    public  void save(
            @RequestParam String name_user,
            @RequestParam String  pass_word

    )throws SQLException {
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            Utilisateur.AddClientUser (con, name_user,pass_word);
        }
    }

    @GetMapping
    public ResponseEntity <Utilisateur> login(@RequestParam String name_user, @RequestParam String pass_word) throws Exception {
        ConnectPost cp = new ConnectPost();
        Utilisateur utilisateur = null;
        try(Connection con = cp.ConnectionBase()) {
            utilisateur = Utilisateur.login(con,name_user,pass_word);
        }
        return ResponseEntity.ok(utilisateur);
    }

}

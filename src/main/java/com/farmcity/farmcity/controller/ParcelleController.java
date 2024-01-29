package com.farmcity.farmcity.controller;
import com.farmcity.farmcity.model.Parcelle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parcelles")
public class ParcelleController {
    @PostMapping
    public void save(
            @RequestParam int id_terrain,
            @RequestParam float rendement,
            @RequestParam int superficie
    ) throws SQLException{
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            Parcelle.AddParcelles(con, id_terrain, rendement, superficie);
        }
    }

    @GetMapping
    public ResponseEntity<List<Parcelle>> getParcelle()throws SQLException{
        ConnectPost cp = new ConnectPost();
        List<Parcelle> parcelles= new ArrayList<>();
        try(Connection con = cp.ConnectionBase()) {
            parcelles = Parcelle.getAllParcelles(con);
        }
        return ResponseEntity.ok(parcelles);
    }
}

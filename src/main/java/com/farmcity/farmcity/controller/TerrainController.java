package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.Culture;
import com.farmcity.farmcity.model.Parcelle;
import com.farmcity.farmcity.model.Terrain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("terrains")
public class TerrainController {
    @PostMapping
    public  void save(
            @RequestParam float longitude,
            @RequestParam float latitude,
            @RequestParam int nombre_parcelle,
            @RequestParam int etat
    )throws SQLException {
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            Terrain.addTerrain(con, longitude, latitude, nombre_parcelle,etat);
        }
    }

    @GetMapping
    public ResponseEntity<List<Terrain>> getAllTerrains()throws SQLException{
        ConnectPost cp = new ConnectPost();
        List<Terrain> terrains = new ArrayList<>();
        try(Connection con = cp.ConnectionBase()) {
            terrains = Terrain.getAllTerrains(con);
        }
        return ResponseEntity.ok(terrains);
    }

}

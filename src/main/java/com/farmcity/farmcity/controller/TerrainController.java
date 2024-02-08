package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.Culture;
import com.farmcity.farmcity.model.Terrain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("terrains")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.OPTIONS})
public class TerrainController {
    
    @PostMapping
    public  void save(
            @RequestParam float longitude,
            @RequestParam float latitude,
            @RequestParam int nombre_parcelle,
            @RequestParam int etat,
            @RequestParam String description
    )throws SQLException {
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            Terrain.addTerrain(con, longitude, latitude, nombre_parcelle,etat,description);
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

    @GetMapping("/validation")
    public Boolean validation() throws SQLException{

        ConnectPost cp = new ConnectPost();
        Connection con = cp.ConnectionBase();
        Boolean val = Terrain.Validation(con , 1,1);
        return val;
    }


}

package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.Terrain;
import com.farmcity.farmcity.model.UserTerrain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("userterrains")
public class UserTerrainController {
    @PostMapping
    public  void save(
            @RequestParam int id_user,
            @RequestParam int id_terrain

    )throws SQLException {
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            UserTerrain.addUser(con, id_user,id_terrain);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserTerrain>> getAllTerrains()throws SQLException{
        ConnectPost cp = new ConnectPost();
        List<UserTerrain> userTerrains = new ArrayList<>();
        try(Connection con = cp.ConnectionBase()) {
            userTerrains = UserTerrain.getUserTerrains(con);
        }
        return ResponseEntity.ok(userTerrains);
    }
}

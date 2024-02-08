package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.HistoriqueTerrain;
import com.farmcity.farmcity.model.Parcelle;
import com.farmcity.farmcity.model.Terrain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("historique")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.OPTIONS})
public class HistoriqueController {
    @PostMapping
    public void save(
            @RequestParam int id_vendeur,
            @RequestParam int id_acheteur,
            @RequestParam Date date_achat
    ) throws SQLException {
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            HistoriqueTerrain.addHistoriqueTerrain(con, id_vendeur, id_acheteur, date_achat);
        }
    }

    @GetMapping
    public ResponseEntity<List<HistoriqueTerrain>> getAllHistorique()throws SQLException{
        ConnectPost cp = new ConnectPost();
        List<HistoriqueTerrain> historiqueTerrains = new ArrayList<>();
        try(Connection con = cp.ConnectionBase()) {
            historiqueTerrains = HistoriqueTerrain.getAllHistorique(con);
        }
        return ResponseEntity.ok(historiqueTerrains);
    }
}

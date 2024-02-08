package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.Culture;
import com.farmcity.farmcity.model.CultureParcelle;
import com.farmcity.farmcity.model.HistoriqueTerrain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cultureparcelles")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.OPTIONS})
public class CultureParcelleController{
    @PostMapping
    public void save(
            @RequestParam int id_parcelle,
            @RequestParam int id_culture,
            @RequestParam Timestamp date_plantation
            ) throws SQLException {
        ConnectPost cp = new ConnectPost();
        try (Connection con = cp.ConnectionBase()) {
            CultureParcelle.AddCultureParcelle(con, id_parcelle, id_culture, date_plantation);
        }
    }
    @GetMapping
   public ResponseEntity<List<CultureParcelle>> getAllCulturesParcelles()throws SQLException{
    ConnectPost cp = new ConnectPost();
    List<CultureParcelle> cultureParcelles = new ArrayList<>();
    try(Connection con = cp.ConnectionBase()) {
        cultureParcelles = CultureParcelle.getAllCulturesParcelles(con);
    }
    return ResponseEntity.ok(cultureParcelles);
}

}

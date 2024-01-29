package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.CultureParcelle;
import com.farmcity.farmcity.model.HistoriqueTerrain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cultureParcelles")
public class CultureParcelleController{
    /*@GetMapping
    public ResponseEntity<List<CultureParcelle>> statCultureParcelle()throws SQLException {
        ConnectPost cp = new ConnectPost();
        List<CultureParcelle> cultureParcelles = new ArrayList<>();
        try(Connection con = cp.ConnectionBase()) {
            cultureParcelles = CultureParcelle.statCultureParcelle(con);
        }
        return ResponseEntity.ok(cultureParcelles);
    }*/
}

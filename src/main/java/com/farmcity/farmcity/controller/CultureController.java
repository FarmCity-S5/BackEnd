package com.farmcity.farmcity.controller;

import com.farmcity.farmcity.model.Culture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cultures")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.OPTIONS})
public class CultureController {
    @PostMapping
    public void save(
            @RequestParam String name_culture,
            @RequestParam int prix,
            @RequestParam Time duration
    ) throws SQLException {
            ConnectPost cp = new ConnectPost();
            try (Connection con = cp.ConnectionBase()) {
                Culture.AddCultures(con, name_culture, prix, duration);
            }
}

@GetMapping("/all")
public ResponseEntity<List<Culture>> getAllCultures()throws SQLException{
    ConnectPost cp = new ConnectPost();
    List<Culture> cultures = new ArrayList<>();
    try(Connection con = cp.ConnectionBase()) {
        cultures = Culture.getAllCultures(con);
    }
    return ResponseEntity.ok(cultures);
}
    @GetMapping("/pourcentageculture")
    public double calculerTempsRestant()throws SQLException{
        ConnectPost cp = new ConnectPost();
        Connection con = cp.ConnectionBase();
        double test = Culture.calculerTempsRestant(con, 5);
        return test;
    }

    @GetMapping("/Hello")
    public String testHello(){
        return("Hello World");
    }



}

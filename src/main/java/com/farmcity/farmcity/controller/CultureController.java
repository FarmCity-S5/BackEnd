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

@GetMapping
public ResponseEntity<List<Culture>> getAllCultures()throws SQLException{
    ConnectPost cp = new ConnectPost();
    List<Culture> cultures = new ArrayList<>();
    try(Connection con = cp.ConnectionBase()) {
        cultures = Culture.getAllCultures(con);
    }
    return ResponseEntity.ok(cultures);
}
}

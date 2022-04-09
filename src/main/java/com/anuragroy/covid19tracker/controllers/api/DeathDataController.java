package com.anuragroy.covid19tracker.controllers.api;

import com.anuragroy.covid19tracker.models.DeathLocationStats;
import com.anuragroy.covid19tracker.models.TotalDeaths;
import com.anuragroy.covid19tracker.services.DeathDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class DeathDataController {

    @Autowired
    private DeathDataService deathDataService;

    @GetMapping("/getDeathLocationStats")
    public ResponseEntity<List<DeathLocationStats>> getDeathLocationStats() {
        try{
            List<DeathLocationStats> deathLocationStats = deathDataService.getDeathLocationStats();
            return new ResponseEntity<>(deathLocationStats, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTotalDeathCases")
    public ResponseEntity<TotalDeaths> getTotalDeathCases() {
        try{
            TotalDeaths totalDeathCases = deathDataService.getTotalDeathCases();
            return new ResponseEntity<TotalDeaths>(totalDeathCases, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

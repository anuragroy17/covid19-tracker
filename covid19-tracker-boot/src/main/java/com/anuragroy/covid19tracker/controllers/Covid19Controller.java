package com.anuragroy.covid19tracker.controllers;

import com.anuragroy.covid19tracker.models.LocationStats;
import com.anuragroy.covid19tracker.models.Totals;
import com.anuragroy.covid19tracker.services.Covid19DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class Covid19Controller {

    @Autowired
    private Covid19DataService covid19DataService;

    @GetMapping("/getCovid19Data")
    public ResponseEntity<List<LocationStats>> getCovid19Data() {
        try{
            List<LocationStats> allStats = covid19DataService.getAllStats();
            return new ResponseEntity<>(allStats, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTotalCases")
    public ResponseEntity<Totals> getTotalCases() {
        try{
            Totals totals = covid19DataService.getTotalCases();
            return new ResponseEntity<Totals>(totals, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

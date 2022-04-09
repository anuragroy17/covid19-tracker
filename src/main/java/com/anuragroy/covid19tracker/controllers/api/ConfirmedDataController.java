package com.anuragroy.covid19tracker.controllers.api;

import com.anuragroy.covid19tracker.models.ConfirmedLocationStats;
import com.anuragroy.covid19tracker.models.TotalConfirmed;
import com.anuragroy.covid19tracker.services.ConfirmedDataService;
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
public class ConfirmedDataController {

    @Autowired
    private ConfirmedDataService confirmedDataService;

    @GetMapping("/getConfirmedLocationStats")
    public ResponseEntity<List<ConfirmedLocationStats>> getConfirmedLocationStats() {
        try{
            List<ConfirmedLocationStats> confirmedLocationStats = confirmedDataService.getConfirmedLocationStats();
            return new ResponseEntity<>(confirmedLocationStats, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTotalConfirmedCases")
    public ResponseEntity<TotalConfirmed> getTotalConfirmedCases() {
        try{
            TotalConfirmed totalConfirmedCases = confirmedDataService.getTotalConfirmedCases();
            return new ResponseEntity<TotalConfirmed>(totalConfirmedCases, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

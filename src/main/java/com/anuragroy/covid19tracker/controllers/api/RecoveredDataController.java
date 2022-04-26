package com.anuragroy.covid19tracker.controllers.api;

import com.anuragroy.covid19tracker.models.RecoveredLocationStats;
import com.anuragroy.covid19tracker.models.TotalRecovered;
import com.anuragroy.covid19tracker.services.RecoveryDataService;
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
public class RecoveredDataController {

    @Autowired
    private RecoveryDataService recoveryDataService;

    @GetMapping("/getRecoveryLocationStats")
    public ResponseEntity<List<RecoveredLocationStats>> getRecoveryLocationStats() {
        try{
            List<RecoveredLocationStats> recoveryLocationStats = recoveryDataService.getRecoveryLocationStats();
            return new ResponseEntity<>(recoveryLocationStats, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTotalRecoveredCases")
    public ResponseEntity<TotalRecovered> getTotalRecoveredCases() {
        try{
            TotalRecovered totalRecoveredCases = recoveryDataService.getTotalRecoveredCases();
            return new ResponseEntity<TotalRecovered>(totalRecoveredCases, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

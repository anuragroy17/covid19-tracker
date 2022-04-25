package com.anuragroy.covid19tracker.controllers;

import com.anuragroy.covid19tracker.services.RecoveryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecoveredController {

    @Autowired
    private RecoveryDataService recoveryDataService;

    @GetMapping({"/getRecoveredData"})
    public ModelAndView getRecoveredData() {
        ModelAndView mav = new ModelAndView("fragments/recovered_data_fragment :: recovered_data");
        mav.addObject("recoveredLocationStats", recoveryDataService.getRecoveryLocationStats());
        mav.addObject("recoveredStats", recoveryDataService.getTotalRecoveredCases());
        return mav;
    }
}

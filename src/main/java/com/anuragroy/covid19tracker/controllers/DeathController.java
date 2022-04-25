package com.anuragroy.covid19tracker.controllers;

import com.anuragroy.covid19tracker.services.DeathDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeathController {

    @Autowired
    private DeathDataService deathDataService;

    @GetMapping({"/getDeathData"})
    public ModelAndView getDeathData() {
        ModelAndView mav = new ModelAndView("death_data_fragment :: death_data");
        mav.addObject("deathLocationStats", deathDataService.getDeathLocationStats());
        mav.addObject("deathStats", deathDataService.getTotalDeathCases());
        return mav;
    }
}

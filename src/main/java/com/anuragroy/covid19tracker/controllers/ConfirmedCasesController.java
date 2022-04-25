package com.anuragroy.covid19tracker.controllers;

import com.anuragroy.covid19tracker.services.ConfirmedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmedCasesController {

    @Autowired
    private ConfirmedDataService confirmedDataService;

    @GetMapping({"/"})
    public String home() {
        return "index";
    }

    @GetMapping({"/getConfirmedLocationStats"})
    public ModelAndView getDeathLocationStats() {
        ModelAndView mav = new ModelAndView("confirmed_cases_fragment :: confirmed_cases");
        mav.addObject("confirmedLocationStats", confirmedDataService.getConfirmedLocationStats());
        return mav;
    }

    @GetMapping({"/getTotalConfirmedCases"})
    public ModelAndView getTotalConfirmedCases() {
        ModelAndView mav = new ModelAndView("confirmed_cases_fragment :: confirmed_cases_stats");
        mav.addObject("totalConfirmedCases", confirmedDataService.getTotalConfirmedCases());
        return mav;
    }
}

package com.anuragroy.covid19tracker.controllers;

import com.anuragroy.covid19tracker.services.ConfirmedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmedController {

    @Autowired
    private ConfirmedDataService confirmedDataService;

    @GetMapping({"/"})
    public String home() {
        return "index";
    }

    @GetMapping({"/getConfirmedData"})
    public ModelAndView getConfirmedData() {
        ModelAndView mav = new ModelAndView("fragments/confirmed_data_fragment :: confirmed_data");
        mav.addObject("confirmedLocationStats", confirmedDataService.getConfirmedLocationStats());
        mav.addObject("confirmedStats", confirmedDataService.getTotalConfirmedCases());
        return mav;
    }
}

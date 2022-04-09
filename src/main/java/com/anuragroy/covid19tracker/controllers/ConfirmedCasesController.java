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

    @GetMapping({"/getConfirmedLocationStats", "/"})
    public ModelAndView getConfirmedLocationStats() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("confirmedLocationStats", confirmedDataService.getConfirmedLocationStats());
        return mav;
    }
}

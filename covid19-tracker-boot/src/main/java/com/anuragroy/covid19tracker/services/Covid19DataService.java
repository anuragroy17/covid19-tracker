package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.LocationStats;
import com.anuragroy.covid19tracker.models.Totals;

import java.util.List;

public interface Covid19DataService {
    List<LocationStats> getAllStats();

    Totals getTotalCases();
}

package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.DeathLocationStats;
import com.anuragroy.covid19tracker.models.TotalDeaths;

import java.util.List;

public interface DeathDataService {
    List<DeathLocationStats> getDeathLocationStats();

    TotalDeaths getTotalDeathCases();
}

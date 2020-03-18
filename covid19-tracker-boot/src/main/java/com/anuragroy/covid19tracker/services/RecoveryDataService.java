package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.RecoveredLocationStats;
import com.anuragroy.covid19tracker.models.TotalRecovered;

import java.util.List;

public interface RecoveryDataService {
    List<RecoveredLocationStats> getRecoveryLocationStats();

    TotalRecovered getTotalRecoveredCases();
}

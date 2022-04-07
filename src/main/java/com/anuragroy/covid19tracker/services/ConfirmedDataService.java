package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.ConfirmedLocationStats;
import com.anuragroy.covid19tracker.models.TotalConfirmed;

import java.util.List;

public interface ConfirmedDataService {
    List<ConfirmedLocationStats> getConfirmedLocationStats();

    TotalConfirmed getTotalConfirmedCases();
}

package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.RecoveredLocationStats;
import com.anuragroy.covid19tracker.models.TotalRecovered;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecoveryDataServiceImpl implements RecoveryDataService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static String VIRUS_DATA_URL_RECOVERIES = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv";
    private List<RecoveredLocationStats> recoveredLocationStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchDeathsData() throws IOException {
        List<RecoveredLocationStats> newStats = new ArrayList<>();

        HttpGet request = new HttpGet(VIRUS_DATA_URL_RECOVERIES);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                StringReader csvBodyReaders = new StringReader(result);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReaders);
                for (CSVRecord record : records) {
                    RecoveredLocationStats locationStat = new RecoveredLocationStats();
                    locationStat.setState(record.get("Province/State"));
                    locationStat.setCountry(record.get("Country/Region"));
                    int latestCases = Integer.parseInt(record.get(record.size() - 1));
                    int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
                    locationStat.setLatestTotalRecovered(latestCases);
                    locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
                    newStats.add(locationStat);
                }
                this.recoveredLocationStats = newStats;
            }
        }
    }

    @Override
    public List<RecoveredLocationStats> getRecoveryLocationStats() {
        return recoveredLocationStats;
    }

    @Override
    public TotalRecovered getTotalRecoveredCases() {
        TotalRecovered totals = new TotalRecovered();
        totals.setTotalReportedRecoveries(recoveredLocationStats.stream().mapToInt(stat -> stat.getLatestTotalRecovered()).sum());
        totals.setTotalNewRecoveries(recoveredLocationStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum());
        return totals;
    }
}
